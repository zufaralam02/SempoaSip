package com.zufaralam02.sempoasip.Student.Challenge.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Student.Challenge.Activities.DetailChallenge;
import com.zufaralam02.sempoasip.Student.Challenge.Activities.History;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelChallenge;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 02/01/2018.
 */

public class AdapterChallenge extends BaseRecyclerAdapter {

    public AdapterChallenge(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterChallenge.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, int position) {

        Holder holder = (AdapterChallenge.Holder) objectHolder;
        ModelChallenge modelChallenge = (ModelChallenge) getItem(position);

        holder.tvChallengeJudul.setText(modelChallenge.getTvJudul());
        holder.tvChallengeKeterangan.setText(modelChallenge.getTvKeterangang());
        holder.tvChallengeHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), History.class);
                v.getContext().startActivity(intent);

            }
        });
        holder.btnChallengeJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailChallenge.class);
                v.getContext().startActivity(intent);
            }
        });


        if (!modelChallenge.isAvailable()) {
//            holder.cardViewChallenge.setBackgroundColor(Color.parseColor("#DEDEDE"));
            holder.cardViewChallenge.setCardBackgroundColor(getContext().getResources().getColor(R.color.bg_challenge_inactive));
            holder.tvChallengeJudul.setTextColor(getContext().getResources().getColor(R.color.text_color_challenge_inactive));
            holder.tvChallengeHistory.setTextColor(getContext().getResources().getColor(R.color.text_color_history_inactive));
            holder.btnChallengeJoin.setBackgroundResource(R.drawable.rectangle_white_button);
            holder.btnChallengeJoin.setTextColor(getContext().getResources().getColor(R.color.text_color_join_inactive));
            holder.btnChallengeJoin.setText("TRAIN");
            holder.btnChallengeJoin.setEnabled(true);
            holder.tvChallengeHistory.setEnabled(true);
        } else {
//            holder.cardViewChallenge.setBackgroundColor(Color.parseColor("#FFFFFF"));
            holder.cardViewChallenge.setCardBackgroundColor(getContext().getResources().getColor(R.color.bg_challenge));
            holder.tvChallengeJudul.setTextColor(getContext().getResources().getColor(R.color.text_color_challenge));
            holder.tvChallengeHistory.setTextColor(getContext().getResources().getColor(R.color.text_color_history));
            holder.btnChallengeJoin.setBackgroundResource(R.drawable.rectangle_blue_button);
            holder.btnChallengeJoin.setTextColor(getContext().getResources().getColor(R.color.text_color_join));
            holder.btnChallengeJoin.setText("JOIN");
            holder.btnChallengeJoin.setEnabled(true);
            holder.tvChallengeHistory.setEnabled(true);

        }

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView tvChallengeJudul, tvChallengeKeterangan, tvChallengeHistory;
        Button btnChallengeJoin;
        CardView cardViewChallenge;

        public Holder(View v) {
            super(v);

            tvChallengeJudul = (TextView) v.findViewById(R.id.tvChallengeJudul);
            tvChallengeKeterangan = (TextView) v.findViewById(R.id.tvChallengeKeterangan);
            tvChallengeHistory = (TextView) v.findViewById(R.id.tvChallengeHistory);
            btnChallengeJoin = (Button) v.findViewById(R.id.btnChallengeJoin);
            cardViewChallenge = (CardView) v.findViewById(R.id.cardViewChallenge);
        }
    }
}
