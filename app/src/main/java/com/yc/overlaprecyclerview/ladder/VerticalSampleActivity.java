package com.yc.overlaprecyclerview.ladder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yc.overlaprecyclerview.R;
import com.yc.overlaprecyclerview.ladder.view.LadderLayoutManager;
import com.yc.overlaprecyclerview.ladder.view.LadderSimpleSnapHelper;
import com.yc.overlaprecyclerview.ladder.view.VerticalSampleChildDecorateHelper;
import com.yc.overlaprecyclerview.ladder.view.VerticalSampleItemLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * 垂直的阶梯
 */

public class VerticalSampleActivity extends AppCompatActivity {
    LadderLayoutManager llm;
    RecyclerView rcv;
    private List<String> mList = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_sample);

        mList.add("1 条");
        mList.add("2 条");
        mList.add("3 条");
        mList.add("4 条");
        mList.add("5 条");
        //        llm = new LadderLayoutManager(0.8f).setChildDecorateHelper(new VerticalSampleChildDecorateHelper(getResources().getDimension(R.dimen.item_max_elevation)));
        llm = new LadderLayoutManager(0.8f).setChildDecorateHelper(new VerticalSampleChildDecorateHelper(getResources().getDimension(R.dimen.item_max_elevation)));
        llm.setMaxItemLayoutCount(7);// 子布局数量限制
        llm.setChildPeekSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 110, getResources().getDisplayMetrics()));
        rcv = (RecyclerView) findViewById(R.id.rcv);
        rcv.setLayoutManager(llm);
        new LadderSimpleSnapHelper().attachToRecyclerView(rcv);
        rcv.setAdapter(new VSAdapter());


    }

    private class VSAdapter extends RecyclerView.Adapter<VSAdapter.VH> {
        private int[] colors = {0xff03a9f4, 0xff259b24, 0xffffeb3b, 0xffff5722, 0xffe51c23, 0xff673ab7};

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(VerticalSampleActivity.this).inflate(R.layout.item_vertical, parent, false));
        }

        @Override
        public void onBindViewHolder(VH holder, final int position) {
            holder.v.setStrokeColor(colors[position % colors.length]);
            holder.tv_content.setText("当前条目 ：" + mList.get(position));

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rcv.smoothScrollToPosition(position);
                }
            });

            //            holder.remove.setOnClickListener(new View.OnClickListener() {
            //                @Override
            //                public void onClick(View view) {
            //                    mList.remove(position - 1);
            //                }
            //            });


        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        class VH extends RecyclerView.ViewHolder {
            VerticalSampleItemLayout v;
            CardView content;
            View remove;
            TextView tv_content;

            public VH(View itemView) {
                super(itemView);
                v = (VerticalSampleItemLayout) itemView.findViewById(R.id.container);
                tv_content = v.findViewById(R.id.tv_content);
                remove = v.findViewById(R.id.remove);
                content = v.findViewById(R.id.content);
                //                content.setOnClickListener(new View.OnClickListener() {
                //                    @Override
                //                    public void onClick(View v) {
                //                        rcv.smoothScrollToPosition(getLayoutPosition());
                //                    }
                //                });
            }
        }
    }
}
