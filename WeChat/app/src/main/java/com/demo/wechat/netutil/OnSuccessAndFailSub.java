package com.demo.wechat.netutil;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.demo.wechat.R;
import com.demo.wechat.weight.CommonDialog;

import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.observers.DisposableObserver;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * 请求网络接口时对话框提示
 * 请求结束后，不管成功或者失败，对话框消失
 * 成功时，调用接口 onSuccess 失败时调用 onFault ，其他状态做统一处理
 * 回调结果为String，需要手动序列化
 */

public class OnSuccessAndFailSub extends DisposableObserver<ResponseBody> implements ProgressCancelListener {

    private boolean isShowProgress = true;
    private OnSuccessAndFailListener mOnSuccessAndFailListener;
    private Context context;
    private CommonDialog commonDialog;

    //设置成功时的回调
    public OnSuccessAndFailSub(OnSuccessAndFailListener onSuccessAndFailListener) {
        this.mOnSuccessAndFailListener = onSuccessAndFailListener;
    }

    public OnSuccessAndFailSub(OnSuccessAndFailListener onSuccessAndFailListener, Context context) {
        this.mOnSuccessAndFailListener = onSuccessAndFailListener;
        this.context = context;
        commonDialog = new CommonDialog(context, R.style.commonDialog);
    }

    public OnSuccessAndFailSub(OnSuccessAndFailListener onSuccessAndFailListener, Context context, boolean isShowProgress) {
        this.mOnSuccessAndFailListener = onSuccessAndFailListener;
        this.context = context;
        this.isShowProgress = isShowProgress;
        commonDialog = new CommonDialog(context, R.style.commonDialog);

    }

    /**
     * 显示对话框
     */
    public void showDialog() {
        if (commonDialog != null && isShowProgress) {
            commonDialog.show();
        }
    }

    /**
     * 取消对话框
     */
    public void dismissDialog() {

        if (commonDialog != null && isShowProgress) {
            commonDialog.dismiss();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showDialog();
    }

    @Override
    public void onCancelProgress() {
        if (!this.isDisposed()) {
            this.dispose();
        }
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
//请求回来的数据进行统一封装
            String result = responseBody.string();
            JSONObject jsonObject = new JSONObject(result);
            int resultCode = jsonObject.getInt("code");
            if (resultCode == 0) {
                if (!TextUtils.isEmpty(result)) {
                    mOnSuccessAndFailListener.onSuccess(result);
                } else {
                    mOnSuccessAndFailListener.onSuccess("数据为空");
                }
            } else {
                String errorMsg = jsonObject.getString("message");
                mOnSuccessAndFailListener.onFault(errorMsg);

            }
        } catch (Exception e) {
            e.printStackTrace();
            mOnSuccessAndFailListener.onFault(e.toString());

        }
    }

    @Override
    public void onError(Throwable e) {


        try {
            if (e instanceof SocketTimeoutException) {//请求超时
                mOnSuccessAndFailListener.onFault("网络请求超时");
            } else if (e instanceof ConnectException) {//网络连接超时
                mOnSuccessAndFailListener.onFault("网络连接超时");
            } else if (e instanceof SSLHandshakeException) {//安全证书异常
                mOnSuccessAndFailListener.onFault("安全证书异常");
            } else if (e instanceof HttpException) {//http状态异常

                int code = ((HttpException) e).code();
                if (code == 505) {
                    mOnSuccessAndFailListener.onFault("接口程序异常");
                } else if (code == 404) {
                    mOnSuccessAndFailListener.onFault("请求的接口地址不存在");
                } else if (code == 504) {
                    mOnSuccessAndFailListener.onFault("服务器网络异常，接口504");
                } else {
                    mOnSuccessAndFailListener.onFault("http网络异常");
                }
            } else if (e instanceof UnknownHostException) {
                mOnSuccessAndFailListener.onFault("未知的域名，域名解析失败");
            } else {
                mOnSuccessAndFailListener.onFault(e.getMessage());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mOnSuccessAndFailListener.onFault("接口异常，请检查");
            dismissDialog();
            commonDialog = null;
        }
    }

    @Override
    public void onComplete() {

        dismissDialog();
        commonDialog = null;
    }
}
