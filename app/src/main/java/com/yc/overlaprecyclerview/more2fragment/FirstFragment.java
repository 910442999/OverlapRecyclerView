package com.yc.overlaprecyclerview.more2fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yc.overlaprecyclerview.R;
import com.yc.overlaprecyclerview.X5WebView;

public class FirstFragment extends Fragment {
    private Bundle mBundle;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfragment, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        X5WebView tv_text = view.findViewById(R.id.tv_text);
        Bundle arguments = getArguments();
        if (arguments != null) {
//            tv_text.setText("当前位置为 : " + arguments.get("position"));
            tv_text.loadUrl("https://www.baidu.com");
        }
    }
}
