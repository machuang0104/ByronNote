package com.byron.note.module;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.byron.library.anno.InjectLayout;
import com.byron.library.anno.InjectView;
import com.byron.note.MainActivity;
import com.byron.note.R;
import com.byron.note.base.AbsActivity;
import com.byron.note.util.ToastUtil;
import com.byron.note.widget.cache.UserCache;

/**
 * Description: Login Page
 */
@InjectLayout(id = R.layout.activity_login)
public class LoginActivity extends AbsActivity {

    @InjectView(id = R.id.etLoginAccount)
    public EditText etAccount;

    @InjectView(id = R.id.btn_login)
    public TextView btn_login;

    private String accountStr;

    @Override
    protected void afterOnCreate() {
        findViewById(R.id.title_left).setVisibility(View.GONE);
        updateTitle(R.string.title_login);
        btn_login.setOnClickListener(mListener);
    }

    OnClickListener mListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btn_login:
                    accountStr = etAccount.getText().toString().trim();

                    if (TextUtils.isEmpty(accountStr)) {
                        ToastUtil.show(R.string.login_tip_pwd_null);
                    } else
                        doLogin(accountStr);
                    break;
                default:
                    break;
            }

        }
    };

    private void doLogin(String account) {
        if (account.equals(UserCache.getPWD())) {
            ToastUtil.show(R.string.login_sucess);
            doActivity(MainActivity.class);
            finish();
        } else {
            ToastUtil.show(R.string.login_tip_pwd_wrong);
        }
    }

    private long firstTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - firstTime < 2500) {
                finish();
                System.exit(0);
            } else {
                firstTime = System.currentTimeMillis();
                ToastUtil.show(R.string.tip_exit);
            }
        }
        return true;
    }

}