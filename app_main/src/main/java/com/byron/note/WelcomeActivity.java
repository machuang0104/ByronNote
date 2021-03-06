package com.byron.note;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.byron.note.base.AbsActivity;
import com.byron.library.db.client.manager.TypeManager;
import com.byron.note.module.LoginActivity;
import com.byron.library.bean.db.TypeVo;
import com.byron.note.widget.cache.UserCache;

import java.util.ArrayList;

/**
 * 延时广告显示页面
 */
public class WelcomeActivity extends AbsActivity {

    ImageView img_homeAD;

    @Override
    protected void afterOnCreate() {
        img_homeAD = new ImageView(this);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        img_homeAD.setLayoutParams(lp);
        img_homeAD.setScaleType(ScaleType.CENTER_CROP);
        // img_homeAD.setImageResource(R.drawable.guide_0);
        setContentView(img_homeAD);

        new ProgressBarAsyncTask().execute(500);
        ArrayList<TypeVo> list = TypeManager.getInstance().findAll();
        if (list == null || list.size() == 0) {
            TypeVo v = new TypeVo();
            v.setCreatTime(System.currentTimeMillis());
            v.setTitle("默认分类");
            v.setType_id(0);
            TypeManager.getInstance().insert(v);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 500);
    }

    public class ProgressBarAsyncTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... arg0) {
            try {
                Thread.sleep(arg0[0]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "";
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            doMainActivity();
        }
    }

    private void doMainActivity() {
        if (TextUtils.isEmpty(UserCache.getPWD())) {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        finish();
    }
}