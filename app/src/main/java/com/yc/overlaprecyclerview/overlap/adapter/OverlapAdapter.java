package com.yc.overlaprecyclerview.overlap.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yc.overlaprecyclerview.R;

public class OverlapAdapter extends RecyclerView.Adapter<OverlapAdapter.ViewHolder> {
    private Context mContext;

    public OverlapAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main, parent, false);
        return new OverlapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OverlapAdapter.ViewHolder holder, final int position) {
        holder.textView.setText("当前位置 : " + position);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "当前位置 : " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_text);
        }

    }
}
