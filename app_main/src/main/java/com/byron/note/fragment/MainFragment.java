package com.byron.note.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.byron.library.bean.db.ContentVo;
import com.byron.library.bean.db.TypeVo;
import com.byron.note.R;
import com.byron.note.adapter.ViewHolders;
import com.byron.note.base.BaseFragment;
import com.byron.library.db.client.manager.ContentManager;
import com.byron.library.db.client.manager.TypeManager;
import com.byron.library.K;
import com.byron.note.module.edit.DetailActivity;
import com.byron.note.module.edit.EditActivity;
import com.byron.note.widget.cache.SharedUtil;
import com.byron.note.widget.listview.XListView;
import com.byron.note.widget.menu.SlidingMenu;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainFragment extends BaseFragment {

    SlidingMenu mMenu;

    public MainFragment() {
    }

    public MainFragment(SlidingMenu menu) {
        this.mMenu = menu;
    }

    View mainView;
    TextView mainTitle;
    XListView recordList;
    ArrayList<ContentVo> dataList = new ArrayList<ContentVo>();
    RecordAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_main, container, false);
        init();
        return mainView;
    }

    private int pageNum = 1;

    public void onTypeChange(String typeTitle, int typeId) {
        mainTitle.setText(typeTitle);
        this.typeId = typeId;
        refreshList();
    }

    private void refreshList() {
        dataList.clear();
        ArrayList<ContentVo> temp = ContentManager.getInstance().findByTypeId(typeId);
        dataList.addAll(temp);
        mAdapter.notifyDataSetChanged();
    }

    private void init() {
        mainView.findViewById(R.id.img_menu).setOnClickListener(mListener);
        mainTitle = (TextView) mainView.findViewById(R.id.main_title);
        mainView.findViewById(R.id.img_menu_right).setOnClickListener(mListener);
        recordList = (XListView) mainView.findViewById(R.id.record_list);
        recordList.setPullLoadEnable(false, false);
        recordList.setPullRefreshEnable(false);
        mAdapter = new RecordAdapter();
        recordList.setAdapter(mAdapter);
        // recordList.setXListViewListener(pullListener);
        recordList.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(K.intent.Detail_Data, dataList.get(position - 1));
                intent.setClass(getActivity(), DetailActivity.class);
                startActivity(intent);
            }
        });
        iniInfo();
    }

    int typeId = 0;

    /**
     * 初始化，无数据，false
     */
    private boolean iniInfo() {
        ArrayList<TypeVo> l = TypeManager.getInstance().findAll();
        if (l == null || l.size() == 0) {
            return false;
        }
        TypeVo t = l.get(0);
        onTypeChange(t.getTitle(), t.getType_id());
        return true;
    }

    XListView.IXListViewListener pullListener = new XListView.IXListViewListener() {
        @Override
        public void onRefresh() {
            pageNum = 1;
            dataList.clear();
            // dataList.addAll(temp);
            // refreshListState(false);
            // refreshListState(true);
            mAdapter.notifyDataSetChanged();
            recordList.stopRefresh();
        }

        @Override
        public void onLoadMore() {
            pageNum++;
            // dataList.addAll(temp);
            // if (temp.size() != 10) {
            // ToastUtils.show(R.string.no_more_data);
            // refreshListState(false);
            // } else
            // refreshListState(true);
            mAdapter.notifyDataSetChanged();
            recordList.stopLoadMore();
        }
    };

    private void refreshListState(boolean loadEnable) {
        recordList.setPullLoadEnable(loadEnable, true);
    }

    public void toggleMenu(View view) {
        if (mMenu != null)
            mMenu.toggleMenu();
    }

    OnClickListener mListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.img_menu:
                    if (mMenu != null)
                        mMenu.toggleMenu();
                    break;
                case R.id.img_menu_right: {
                    Intent intent = new Intent();
                    intent.putExtra(K.intent.TYPE_ID, typeId);
                    intent.setClass(getActivity(), EditActivity.class);
                    startActivity(intent);
                    break;
                }
                default:
                    break;
            }

        }
    };

    class RecordAdapter extends BaseAdapter {
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_list, parent, false);
            TextView title = ViewHolders.get(convertView, R.id.record_title);
            TextView time = ViewHolders.get(convertView, R.id.record_time);
            ContentVo r = dataList.get(position);
            title.setText(r.getTitle());
            time.setText(getAlarmTime(r.getCreatetime()));
            return convertView;
        }
    }

    public String getAlarmTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(time));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SharedUtil.getBoolean(K.intent.Need_Refresh)) {
            refreshList();
            SharedUtil.clear(K.intent.Need_Refresh);
        }

    }

}