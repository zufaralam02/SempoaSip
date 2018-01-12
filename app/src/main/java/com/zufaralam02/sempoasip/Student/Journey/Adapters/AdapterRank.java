package com.zufaralam02.sempoasip.Student.Journey.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Student.Journey.Models.ModelRank;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 22/12/2017.
 */

public class AdapterRank extends BaseRecyclerAdapter {

    public AdapterRank(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterRank.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, int position) {

        Holder holder = (AdapterRank.Holder) objectHolder;
        ModelRank modelRank = (ModelRank) getItem(position);

        holder.tvRankNumber.setText(modelRank.getTvRankNumber());
        holder.tvRankName.setText(modelRank.getTvRankName());
        holder.tvRankTime.setText(modelRank.getTvRankTime());

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    class Holder extends RecyclerView.ViewHolder {
        TextView tvRankNumber, tvRankName, tvRankTime;

        public Holder(View itemView) {
            super(itemView);

            tvRankNumber = (TextView) itemView.findViewById(R.id.tvRankNumber);
            tvRankName = (TextView) itemView.findViewById(R.id.tvRankName);
            tvRankTime = (TextView) itemView.findViewById(R.id.tvRankTime);
        }
    }
}
