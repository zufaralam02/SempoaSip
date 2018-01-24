package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class AccountSetting extends BaseActivitySempoa implements View.OnClickListener {
    TextView tvNameAccountSetting, tvEmailAccountSetting, tvPhoneAccountSetting, tvPassAccountSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        setupNav("Account Setting");
        initialization();
    }

    private void initialization() {
        tvNameAccountSetting = findViewById(R.id.tvNameAccountSetting);
        tvEmailAccountSetting = findViewById(R.id.tvEmailAccountSetting);
        tvPhoneAccountSetting = findViewById(R.id.tvPhoneAccountSetting);
        tvPassAccountSetting = findViewById(R.id.tvPassAccountSetting);

        tvNameAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSetting.this, ChangeName.class));
            }
        });

        tvEmailAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSetting.this, ChangeEmail.class));
            }
        });

        tvPhoneAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSetting.this, ChangePhoneNumber.class));
            }
        });

        tvPassAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountSetting.this, ChangePassword.class));
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
