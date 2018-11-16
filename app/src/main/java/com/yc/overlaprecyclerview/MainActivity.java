package com.yc.overlaprecyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yc.overlaprecyclerview.echelon.EchelonActivity;
import com.yc.overlaprecyclerview.ladder.VerticalSampleActivity;
import com.yc.overlaprecyclerview.more2fragment.MoreFagmentActivity;
import com.yc.overlaprecyclerview.morefragment.Main2Activity;
import com.yc.overlaprecyclerview.overlap.OverlapActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        TextView textView1 = findViewById(R.id.text1);
        textView1.setOnClickListener(this);

        TextView textView2 = findViewById(R.id.text2);
        textView2.setOnClickListener(this);

        TextView textView3 = findViewById(R.id.text3);
        textView3.setOnClickListener(this);
        TextView text4 = findViewById(R.id.text4);
        text4.setOnClickListener(this);
        TextView text5 = findViewById(R.id.text5);
        text5.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text1:
                mIntent = new Intent(this, OverlapActivity.class);
                startActivity(mIntent);
                break;

            case R.id.text2:
                mIntent = new Intent(this, VerticalSampleActivity.class);
                startActivity(mIntent);
                break;

            case R.id.text3:
                mIntent = new Intent(this, EchelonActivity.class);
                startActivity(mIntent);
                break;

            case R.id.text4:
                mIntent = new Intent(this, Main2Activity.class);
                startActivity(mIntent);
                break;
            case R.id.text5:
                mIntent = new Intent(this, MoreFagmentActivity.class);
                startActivity(mIntent);
                break;


        }
    }
}
