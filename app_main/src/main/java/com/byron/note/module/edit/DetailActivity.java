package com.byron.note.module.edit;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.byron.library.anno.InjectLayout;
import com.byron.library.anno.InjectView;
import com.byron.note.R;
import com.byron.note.base.AbsActivity;
import com.byron.library.db.client.manager.ContentManager;
import com.byron.library.K;
import com.byron.note.util.ToastUtil;
import com.byron.library.bean.db.ContentVo;
import com.byron.note.widget.cache.SharedUtil;
import com.byron.note.widget.dialog.DialogClient;

@InjectLayout(id = R.layout.activity_detail)
public class DetailActivity extends AbsActivity {

	@InjectView(id = R.id.input_title)
	TextView title;
	@InjectView(id = R.id.input_content)
	TextView content;

	ContentVo detail;

	@Override
	protected void afterOnCreate() {
		detail = (ContentVo) getIntent().getSerializableExtra(K.intent.Detail_Data);
		title.setText(detail.getTitle());
		content.setText(detail.getContent());
		setTitleRightStr(R.string.key_delete);
	}

	@Override
	public void onClickTitleStr(View v) {
		super.onClickTitleStr(v);
		DialogClient.showTwo(DetailActivity.this, getString(R.string.tip_delete), new OnClickListener() {

			@Override
			public void onClick(View v) {
				ContentManager.getInstance().delete(detail.getId());
				ToastUtil.show(R.string.tip_delete_sucess);
				SharedUtil.saveBoolean(K.intent.Need_Refresh, true);
				finish();
			}
		});
	}

}