package com.byron.note.module.set;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.byron.library.anno.InjectLayout;
import com.byron.library.anno.InjectView;
import com.byron.note.R;
import com.byron.note.base.AbsActivity;

/**
 * Description: Login Page
 * 
 * 
 */
@InjectLayout(id = R.layout.activity_setting)
public class SettingActivity extends AbsActivity {

	@InjectView(id = R.id.set_pwd)
	public TextView set_pwd;

	@Override
	protected void afterOnCreate() {
		updateTitle(R.string.menu_set);
		set_pwd.setOnClickListener(mListener);
	}

	OnClickListener mListener = new OnClickListener() {
		@Override
		public void onClick(View v) {

			switch (v.getId()) {
			case R.id.set_pwd:
				doActivity(SetPwdActivity.class);
				break;
			default:
				break;
			}

		}
	};

}