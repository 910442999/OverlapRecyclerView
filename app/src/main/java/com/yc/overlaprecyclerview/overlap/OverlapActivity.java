package com.yc.overlaprecyclerview.overlap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.yc.overlaprecyclerview.R;
import com.yc.overlaprecyclerview.overlap.adapter.OverlapAdapter;
import com.yc.overlaprecyclerview.overlap.view.OverlapLayoutManager;

/**
 * 自定义recyclview 层叠效果
 */
public class OverlapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overlap);
        initView();
    }

    private void initView() {
        RecyclerView recyclerView = findViewById(R.id.rv);
        OverlapLayoutManager overlapLayoutManager = new OverlapLayoutManager();
        recyclerView.setLayoutManager(overlapLayoutManager);
        recyclerView.setAdapter(new OverlapAdapter(this));
    }
}
