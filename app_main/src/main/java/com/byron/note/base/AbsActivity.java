package com.byron.note.base;


import android.content.res.Resources.NotFoundException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.byron.library.base.BaseActivity;
import com.byron.library.log.LogUtil;
import com.byron.note.R;

public abstract class AbsActivity extends BaseActivity {

    protected void updateTitle(int strResId) {
        try {
            TextView title = (TextView) findViewById(R.id.title_tv);
            if (title != null) {
                title.setVisibility(View.VISIBLE);
                title.setText(strResId);
            }
        } catch (NotFoundException e) {
            LogUtil.e("tag_activity", "update title failed : " + this.getClass().getSimpleName());
        }
    }

    /**
     * 设置右侧标题栏图片
     */
    protected void setTitleRgihtImg(int resId) {
        ImageView title = (ImageView) findViewById(R.id.title_right_img);
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            title.setImageResource(resId);
        }
    }

    /**
     * 设置右侧标题栏文字
     */
    protected void setTitleRightStr(int resId) {
        TextView title = (TextView) findViewById(R.id.title_right_txt);
        if (title != null) {
            title.setVisibility(View.VISIBLE);
            title.setText(resId);
        }
    }

    public void onClickTitleLeft(View v) {
        if (v.getId() == R.id.title_left)
            finish();
    }

    /**
     * 点击标题右侧图片
     */
    public void onClickTitleImg(View v) {

    }

    /**
     * 点击标题右侧文字
     */
    public void onClickTitleStr(View v) {

    }
}
