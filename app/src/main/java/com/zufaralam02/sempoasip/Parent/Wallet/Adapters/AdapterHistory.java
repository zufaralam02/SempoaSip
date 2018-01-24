package com.zufaralam02.sempoasip.Parent.Wallet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Parent.Wallet.Activities.TopupCoin;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelHistory;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelWallet;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 19/01/2018.
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

        AdapterHistory.Holder holder = (AdapterHistory.Holder) objectHolder;
        ModelHistory modelHistory = (ModelHistory) getItem(position);

        holder.tvNameWalletHistory.setText(modelHistory.getHistoryName());
        holder.tvTimeWalletHistory.setText(modelHistory.getHistoryTime());
        holder.tvCoinWalletHistory.setText(modelHistory.getHistoryCoin());

        if (modelHistory.isPending()) {
            holder.tvPendingWalletHistory.setVisibility(View.VISIBLE);
        } else {
            holder.tvPendingWalletHistory.setVisibility(View.GONE);
        }

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView tvNameWalletHistory, tvTimeWalletHistory, tvCoinWalletHistory, tvPendingWalletHistory;

        public Holder(View v) {
            super(v);

            tvNameWalletHistory = v.findViewById(R.id.tvNameWalletHistory);
            tvTimeWalletHistory = v.findViewById(R.id.tvTimeWalletHistory);
            tvCoinWalletHistory = v.findViewById(R.id.tvCoinWalletHistory);
            tvPendingWalletHistory = v.findViewById(R.id.tvPendingWalletHistory);
        }
    }
}
