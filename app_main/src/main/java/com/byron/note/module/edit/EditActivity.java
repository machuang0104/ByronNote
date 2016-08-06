package com.byron.note.module.edit;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
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

@InjectLayout(id = R.layout.activity_edit)
public class EditActivity extends AbsActivity {

    @InjectView(id = R.id.input_title)
    EditText title;
    @InjectView(id = R.id.input_content)
    EditText content;

    @InjectView(id = R.id.save)
    TextView save;

    int typeId = -1;

    @Override
    protected void afterOnCreate() {
        initAdd();
    }

    private void initAdd() {
        typeId = getIntent().getIntExtra(K.intent.TYPE_ID, 0);
        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentVo c = new ContentVo();
                c.setContent(content.getText().toString());
                c.setTitle(title.getText().toString());
                c.setCreatetime(System.currentTimeMillis());
                c.setType_id(typeId);
                ContentManager.getInstance().insertUpdate(c);

                ToastUtil.show(R.string.save_sucess);
                SharedUtil.saveBoolean(K.intent.Need_Refresh, true);
                finish();
            }
        });
    }

}