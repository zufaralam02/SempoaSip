package com.zufaralam02.sempoasip.Parent.Wallet.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.Wallet.Fragments.FragmentWalletParent;
import com.zufaralam02.sempoasip.R;

public class TopupCoinDetail extends BaseActivitySempoa implements View.OnClickListener {
    Button btnDoneTopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_coin_detail);

        setupNav("Topup Coin");
        initialization();
        onClickWidget();
    }

    private void initialization() {
        btnDoneTopup = findViewById(R.id.btnDoneTopup);
    }

    private void onClickWidget() {
        btnDoneTopup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDoneTopup:
                finish();
                break;
        }
    }
}
