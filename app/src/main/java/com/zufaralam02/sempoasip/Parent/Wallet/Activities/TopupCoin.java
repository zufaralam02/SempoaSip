package com.zufaralam02.sempoasip.Parent.Wallet.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class TopupCoin extends BaseActivitySempoa implements View.OnClickListener {
    Button btnPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_coin);

        setupNav("Topup Coin");
        initialization();
        onClickWidget();
    }

    private void initialization() {
        btnPayNow = findViewById(R.id.btnPayNow);
    }

    private void onClickWidget() {
        btnPayNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPayNow:
                startActivity(new Intent(this, TopupCoinDetail.class));
                finish();
                break;
        }

    }
}
