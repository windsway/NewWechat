package com.demo.wechat.weight;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.demo.wechat.R;

public class CommonDialog extends ProgressDialog{
    public CommonDialog(Context context) {
        super(context);
    }
    public CommonDialog(Context context,int theme){
        super(context,theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setCancelable(false);//设置不可取消
        setCanceledOnTouchOutside(false);

        setContentView(R.layout.dialog_progress);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}
