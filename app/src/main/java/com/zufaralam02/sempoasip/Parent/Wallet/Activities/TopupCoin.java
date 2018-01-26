package com.zufaralam02.sempoasip.Parent.Wallet.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TopupCoin extends BaseActivitySempoa {

    @BindView(R.id.tvWeeklyChallenge)
    TextView tvWeeklyChallenge;
    @BindView(R.id.llWeeklyChallenge)
    LinearLayout llWeeklyChallenge;
    @BindView(R.id.tvNationalChallenge)
    TextView tvNationalChallenge;
    @BindView(R.id.llNationalChallenge)
    LinearLayout llNationalChallenge;
    @BindView(R.id.tvRegionalChallenge)
    TextView tvRegionalChallenge;
    @BindView(R.id.llRegionalChallenge)
    LinearLayout llRegionalChallenge;
    @BindView(R.id.tvSouvenirSempoa1)
    TextView tvSouvenirSempoa1;
    @BindView(R.id.llSouvenirSempoa1)
    LinearLayout llSouvenirSempoa1;
    @BindView(R.id.tvSouvenirSempoa2)
    TextView tvSouvenirSempoa2;
    @BindView(R.id.llSouvenirSempoa2)
    LinearLayout llSouvenirSempoa2;
    @BindView(R.id.tvSouvenirSempoa3)
    TextView tvSouvenirSempoa3;
    @BindView(R.id.llSouvenirSempoa3)
    LinearLayout llSouvenirSempoa3;
    @BindView(R.id.edtAmountToBuy)
    TextInputEditText edtAmountToBuy;
    @BindView(R.id.btnPayNow)
    Button btnPayNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_coin);
        ButterKnife.bind(this);

        setupNav("Topup Coin");
    }

    @OnClick({R.id.llWeeklyChallenge, R.id.llNationalChallenge, R.id.llRegionalChallenge, R.id.llSouvenirSempoa1, R.id.llSouvenirSempoa2, R.id.llSouvenirSempoa3, R.id.btnPayNow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llWeeklyChallenge:
                edtAmountToBuy.setText(tvWeeklyChallenge.getText().toString());
                break;
            case R.id.llNationalChallenge:
                edtAmountToBuy.setText(tvNationalChallenge.getText().toString());
                break;
            case R.id.llRegionalChallenge:
                edtAmountToBuy.setText(tvRegionalChallenge.getText().toString());
                break;
            case R.id.llSouvenirSempoa1:
                edtAmountToBuy.setText(tvSouvenirSempoa1.getText().toString());
                break;
            case R.id.llSouvenirSempoa2:
                edtAmountToBuy.setText(tvSouvenirSempoa2.getText().toString());
                break;
            case R.id.llSouvenirSempoa3:
                edtAmountToBuy.setText(tvSouvenirSempoa3.getText().toString());
                break;
            case R.id.btnPayNow:
                startActivity(new Intent(getApplicationContext(), TopupCoinDetail.class));
                break;
        }
    }
}
