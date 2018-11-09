package com.yc.overlaprecyclerview.echelon;

import android.content.Intent;
import android.graphics.Rect;
import android.provider.FontRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yc.overlaprecyclerview.R;
import com.yc.overlaprecyclerview.echelon.bean.EchelonBean;
import com.yc.overlaprecyclerview.echelon.view.EchelonLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 梯形布局
 */
public class EchelonActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EchelonLayoutManager mLayoutManager;
    private List<EchelonBean> mList = new ArrayList();
    private int[] icons = {R.mipmap.header_icon_1, R.mipmap.header_icon_2, R.mipmap.header_icon_3, R.mipmap.header_icon_4, R.mipmap.bg_1, R.mipmap.bg_2, R.mipmap.bg_3, R.mipmap.bg_4};
    private MyAdapter mMyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_echelon);
        for (int i = 0; i < 4; i++) {
            EchelonBean echelonBean = new EchelonBean();
            echelonBean.setTitle(i + " 条");
            echelonBean.setIcon(icons[i % 8]);
            mList.add(echelonBean);
        }
        initData();
    }

    private void initData() {
        TextView textView = findViewById(R.id.tv_add);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 1; i++) {
                    EchelonBean echelonBean = new EchelonBean();
                    echelonBean.setTitle(i + " 条");
                    echelonBean.setIcon(icons[i % 8]);
                    mList.add(echelonBean);
                }
                mMyAdapter.notifyDataSetChanged();
            }
        });
        mRecyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new EchelonLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mMyAdapter = new MyAdapter();
        mRecyclerView.setAdapter(mMyAdapter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private int[] bgs = {};

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelon, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Log.e("tag", "当前位置 ： " + (position));
            if (position == 0) {
                holder.tv_text.setText("首页");
                holder.tv_remove.setVisibility(View.GONE);
            } else {
                holder.tv_remove.setVisibility(View.VISIBLE);
                holder.tv_text.setText("标题 ： " + mList.get(position).getTitle());
            }
            holder.icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EchelonBean echelonBean = new EchelonBean();
                    echelonBean.setTitle((mList.size()) + " 条");
                    echelonBean.setIcon(icons[mList.size() % 4]);
                    mList.add(echelonBean);
                    //                    mRecyclerView.scrollToPosition(0);
                    notifyDataSetChanged();
                    mRecyclerView.smoothScrollToPosition(0);
                }
            });
            holder.tv_remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("tag", "移除位置 ： " + (position));
                    if (position > 0) {
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            holder.bg.setImageResource(mList.get(position).getIcon());
            holder.bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("tag", "点击条目位置 ： " + (position));
                }
            });

        }

        @Override
        public int getItemCount() {
            return mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView tv_text;
            TextView tv_remove;
            ImageView bg;

            public ViewHolder(View itemView) {
                super(itemView);
                icon = itemView.findViewById(R.id.img_icon);
                tv_text = itemView.findViewById(R.id.tv_text);
                tv_remove = itemView.findViewById(R.id.tv_remove);
                bg = itemView.findViewById(R.id.img_bg);
            }
        }
    }

}
