package com.byron.note;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.byron.library.anno.InjectLayout;
import com.byron.library.anno.InjectView;
import com.byron.library.bean.db.TypeVo;
import com.byron.library.bean.weather.WeatherDataVo;
import com.byron.library.bean.weather.WeatherForecastVo;
import com.byron.library.bean.weather.WeatherStatusVo;
import com.byron.library.db.client.manager.TypeManager;
import com.byron.library.http.result.MCallBack;
import com.byron.library.http.taskiml.ActionImpl;
import com.byron.note.adapter.ViewHolders;
import com.byron.note.base.AbsActivity;
import com.byron.note.fragment.MainFragment;
import com.byron.note.module.set.SettingActivity;
import com.byron.note.util.ToastUtil;
import com.byron.note.widget.cache.UserCache;
import com.byron.note.widget.dialoginput.DialogInputClient;
import com.byron.note.widget.dialoginput.InputListener;
import com.byron.note.widget.menu.SlidingMenu;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;

@InjectLayout(id = R.layout.activity_main)
public class MainActivity extends AbsActivity {
    @InjectView(id = R.id.sliding_menu)
    private SlidingMenu mMenu;
    @InjectView(id = R.id.typeList)
    ListView typeList;
    @InjectView(id = R.id.menu_weather_city)
    TextView city;
    @InjectView(id = R.id.menu_weather)
    TextView weather;

    ArrayList<TypeVo> dataList = new ArrayList<TypeVo>();

    TypeAdapter mAdappter = new TypeAdapter();

    @Override
    protected void afterOnCreate() {
        mainFragment = new MainFragment(mMenu);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_root, mainFragment).commit();
        findViewById(R.id.add_type).setOnClickListener(menuListener);
        findViewById(R.id.set).setOnClickListener(menuListener);
        typeList.setAdapter(mAdappter);
        mMenu.setStatusListener(new SlidingMenu.OpenStatusListener() {
            @Override
            public void open() {
                if (firstOpen) {
                    initMenu();
                }
                firstOpen = false;
            }

            @Override
            public void close() {
            }
        });
        typeList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                mMenu.closeMenu();
                onMenuClicked(position);
            }
        });
        getLocation();
    }

    private void onMenuClicked(int position) {
        int size = dataList.size();
        if (position >= size || size == 0)
            return;
        if (position != -1) {
            mainFragment.onTypeChange(dataList.get(position).getTitle(),
                    dataList.get(position).getType_id());
        } else {
            mainFragment.onTypeChange(dataList.get(size - 1).getTitle(),
                    dataList.get(size - 1).getType_id());
        }
    }

    private void getWeather() {
        String cityName = UserCache.getCity();
        if (TextUtils.isEmpty(cityName)) {
            cityName = "苏州";
        }
        ActionImpl.getInstance().getWeather(cityName, new MCallBack<WeatherStatusVo>() {
            @Override
            public void onResponse(WeatherStatusVo res, int id) {
                if (res == null) {
                    return;
                }
                if (res.getDesc().equals("OK")) {
                    try {
                        showWeather(res.getData());
                    } catch (Exception e) {
                        ToastUtil.show(R.string.weather_fail);
                    }
                } else {
                    ToastUtil.show(R.string.weather_fail);
                }
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                ToastUtil.show(R.string.weather_fail);
            }
        });
    }

    private void showWeather(WeatherDataVo wea) {
        ArrayList<WeatherForecastVo> list = wea.getForecast();
        StringBuffer w = new StringBuffer("\n");
        if (list != null && list.size() > 0) {
            WeatherForecastVo fo = list.get(0);
            w.append(fo.getHigh()).append(" - ").append(fo.getLow())
                    .append("\n").append(fo.getFengxiang()).append(" - ")
                    .append(fo.getFengli()).append("\n").append(fo.getType())
                    .append("\n");
            city.setText(wea.getCity() + " - " + fo.getDate());
        }
        w.append(wea.getGanmao());
        weather.setText(w.toString());
    }

    private boolean firstOpen = true;

    MainFragment mainFragment;

    private void initMenu() {
        dataList.clear();
        dataList.addAll(TypeManager.getInstance().findAll());
        mAdappter.notifyDataSetChanged();
    }

    OnClickListener menuListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!mMenu.getMenuState())
                return;
            switch (v.getId()) {
                case R.id.add_type:
                    DialogInputClient.show(MainActivity.this, new InputListener() {
                        @Override
                        public void inputDone(String input) {
                            if (!TextUtils.isEmpty(input)) {
                                TypeVo type = new TypeVo();
                                type.setCreatTime(System.currentTimeMillis());
                                type.setTitle(input);
                                TypeManager.getInstance().insert(type);
                                dataList.clear();
                                dataList.addAll(TypeManager.getInstance().findAll());
                                mAdappter.notifyDataSetChanged();
                                onMenuClicked(-1);
                            }
                        }
                    });
                    break;
                case R.id.set:
                    doActivity(SettingActivity.class);
                    break;
                default:
                    break;
            }
        }
    };

    public void toggleMenu(View view) {
        mMenu.toggleMenu();
    }

    private long firstTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 点击两次退出应用程序处理逻辑
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if (mMenu.getMenuState()) {
                mMenu.closeMenu();
                return true;
            }
            if (System.currentTimeMillis() - firstTime < 2500) {
                MainActivity.this.finish();
                System.exit(0);
            } else {
                firstTime = System.currentTimeMillis();
                ToastUtil.show(R.string.tip_exit);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        mainFragment.setUserVisibleHint(hasFocus);
    }

    class TypeAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            if (v == null) {
                v = LayoutInflater.from(MainActivity.this).inflate(
                        R.layout.item_type, parent, false);
            }
            TextView typeTxt = ViewHolders.get(v, R.id.type_title);
            typeTxt.setText(dataList.get(position).getTitle());
            return v;
        }

    }

    private LocationClient mLocationClient;
    private BDLocationListener mLocationListener;

    private void getLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationListener = new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                locationSucess(bdLocation.getCity());
            }
        };
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        option.setScanSpan(0);//可选，定位频率，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
//        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(true);
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(mLocationListener);
        mLocationClient.start();
    }

    private void locationSucess(String city) {
        if (!TextUtils.isEmpty(city)) {
            if (city.contains("市")) {
                city = city.replace("市", "");
            } else if (city.contains("县")) {
                city = city.replace("县", "");
            }
            UserCache.saveCity(city);
        } else {
        }
        sHA1(MainActivity.this);
        getWeather();
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            Log.d("machuang", " SHA1 = " + hexString);
            return hexString.toString();
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}