package com.demo.wechat.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), null);
        unbinder = ButterKnife.bind(getActivity());
        initData();
        return view;

    }

    public BaseFragment(){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


    protected abstract int getLayoutId();

    protected abstract void initData();

    protected void $startActivity(Class<?> cls) {
        $startActivity(cls, null);
    }

    protected void $startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void $startActivityForResult(Class<?> cls, int requestCode) {
        $startActivityForResult(cls, null, requestCode);
    }

    protected void $startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void $toastLong(CharSequence msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected void $toastShort(CharSequence msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    protected Bundle $getIntentExtra() {
        Intent intent = getActivity().getIntent();
        Bundle bundle = null;
        if (null != intent) {
            bundle = intent.getExtras();
        }
        return bundle;
    }

}
