package com.byron.library.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.byron.library.anno.Injector;
import com.byron.library.log.LogUtil;

import com.byron.library.R;
/**
 * Created by admin on 2016/7/27.
 */
public abstract class BaseActivity extends AppCompatActivity {


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        return v;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.injectLayout(this);
        Injector.injectViews(this);
        LogUtil.i("tag_activity", "oncreate-->"
                + this.getClass().getSimpleName());

        afterOnCreate();
    }

    protected abstract void afterOnCreate();

    /**
     * addSwipeBack: 实现滑动返回，设置后需进行显示测试，activity主题需设置为trslucant
     */

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("tag_activity", "onDestroy-->"
                + this.getClass().getSimpleName());

    }

    protected void changeBackGround(boolean dismiss) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        if (dismiss)
            lp.alpha = 1.0f;
        else
            lp.alpha = 0.7f;
        getWindow().setAttributes(lp);
    }

    protected void setSoftInputVisible(View view, boolean visible) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (visible == isOpen)
            return;
        if (visible)
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        else
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    protected void doActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void doActivity(Intent intent) {
        startActivity(intent);
    }

    protected void hideInputMethod() {
        try {
            InputMethodManager iman = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            iman.hideSoftInputFromWindow(this.getCurrentFocus()
                    .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            // iman.showSoftInput(v, InputMethodManager.SHOW_FORCED);
            // iman.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT,
            // InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
        }
    }

}