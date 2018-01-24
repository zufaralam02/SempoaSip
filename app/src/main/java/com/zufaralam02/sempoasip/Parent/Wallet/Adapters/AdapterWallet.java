package com.zufaralam02.sempoasip.Parent.Wallet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.zufaralam02.sempoasip.Parent.Notification.Adapters.AdapterNotification;
import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotification;
import com.zufaralam02.sempoasip.Parent.Wallet.Activities.TopupCoin;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelWallet;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 19/01/2018.
 */

public class AdapterWallet extends BaseRecyclerAdapter {

    public AdapterWallet(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterWallet.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, int position) {

        AdapterWallet.Holder holder = (AdapterWallet.Holder) objectHolder;
        ModelWallet modelWallet = (ModelWallet) getItem(position);

        holder.tvWalletName.setText(modelWallet.getWalletName());
        holder.tvWalletCoin.setText(modelWallet.getWalletCoin());
        holder.btnWalletTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(new Intent(getContext(), TopupCoin.class));
            }
        });

        if (modelWallet.isPeding()) {
            holder.btnWalletPending.setVisibility(View.VISIBLE);
            holder.btnWalletPending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), TopupCoin.class));
                }
            });
        } else {
            holder.btnWalletPending.setVisibility(View.GONE);
        }

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        TextView tvWalletName, tvWalletCoin;
        Button btnWalletTopup, btnWalletPending;

        public Holder(View v) {
            super(v);

            tvWalletName = v.findViewById(R.id.tvWalletName);
            tvWalletCoin = v.findViewById(R.id.tvWalletCoin);
            btnWalletTopup = v.findViewById(R.id.btnWalletTopup);
            btnWalletPending = v.findViewById(R.id.btnWalletPending);
        }
    }
}
