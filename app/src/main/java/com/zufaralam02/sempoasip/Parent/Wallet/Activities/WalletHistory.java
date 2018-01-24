package com.zufaralam02.sempoasip.Parent.Wallet.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.Wallet.Adapters.AdapterHistory;
import com.zufaralam02.sempoasip.Parent.Wallet.Adapters.AdapterWallet;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelHistory;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelWallet;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class WalletHistory extends BaseActivitySempoa {
    RecyclerView recyclerWalletHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_history);

        setupNav("SIP Wallet History");
        initialization();
        setupRecyclerView();
    }

    private void initialization() {
        recyclerWalletHistory = findViewById(R.id.recyclerWalletHistory);
    }

    private void setupRecyclerView() {
        ArrayList<ModelHistory> modelHistory = walletHistoryData();
        AdapterHistory adapterHistory = new AdapterHistory(this, modelHistory, R.layout.list_history_wallet);
        BaseHelper.setupRecyclerView(recyclerWalletHistory, adapterHistory);
    }

    private ArrayList<ModelHistory> walletHistoryData() {

        ArrayList<ModelHistory> modelHistory = new ArrayList<>();

        modelHistory.add(new ModelHistory(R.string.wallet_history_name1, R.string.wallet_history_time1,
                R.string.wallet_history_coin1, true));
        modelHistory.add(new ModelHistory(R.string.wallet_history_name2, R.string.wallet_history_time2,
                R.string.wallet_history_coin2, false));
        modelHistory.add(new ModelHistory(R.string.wallet_history_name3, R.string.wallet_history_time3,
                R.string.wallet_history_coin3, false));
        modelHistory.add(new ModelHistory(R.string.wallet_history_name4, R.string.wallet_history_time4,
                R.string.wallet_history_coin4, false));
        modelHistory.add(new ModelHistory(R.string.wallet_history_name5, R.string.wallet_history_time5,
                R.string.wallet_history_coin5, true));

        return modelHistory;

    }
}
