package com.zufaralam02.sempoasip.Student.Challenge.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelHistory;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 02/01/2018.
 */

public class AdapterHistory extends BaseRecyclerAdapter {

    public AdapterHistory(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterHistory.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, int position) {

        Holder holder = (AdapterHistory.Holder) objectHolder;
        ModelHistory modelHistory = (ModelHistory) getItem(position);

        holder.tvChallangeJudulHistory.setText(modelHistory.getTvJudulHistory());
        holder.tvChallangeYourScore.setText(modelHistory.getTvYourScroeHistory());
        holder.tvChallangeHighScore.setText(modelHistory.getTvHighScoreHistory());

    }


    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView tvChallangeJudulHistory, tvChallangeYourScore, tvChallangeHighScore;

        public Holder(View v) {
            super(v);

            tvChallangeJudulHistory = (TextView) v.findViewById(R.id.tvChallangeJudulHistory);
            tvChallangeYourScore = (TextView) v.findViewById(R.id.tvChallangeYourScore);
            tvChallangeHighScore = (TextView) v.findViewById(R.id.tvChallangeHighScore);
        }
    }
}
