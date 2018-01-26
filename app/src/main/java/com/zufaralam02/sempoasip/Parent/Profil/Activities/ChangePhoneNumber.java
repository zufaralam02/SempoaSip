package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneNumber extends BaseActivitySempoa {

    @BindView(R.id.tvPhoneChangePhone)
    TextView tvPhoneChangePhone;
    @BindView(R.id.edtPhoneChangePhone)
    TextInputEditText edtPhoneChangePhone;
    @BindView(R.id.edtPassChangePhone)
    TextInputEditText edtPassChangePhone;
    @BindView(R.id.btnSaveChangePhone)
    Button btnSaveChangePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        ButterKnife.bind(this);

        setupNav("Change Phone Number");

    }

    @OnClick(R.id.btnSaveChangePhone)
    public void onClick() {
        tvPhoneChangePhone.setText(edtPhoneChangePhone.getText().toString());
        startActivity(new Intent(getApplicationContext(), AccountSetting.class));
    }
}
