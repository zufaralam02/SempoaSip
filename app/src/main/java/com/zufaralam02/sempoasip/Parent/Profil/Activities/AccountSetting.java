package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AccountSetting extends BaseActivitySempoa {

    @BindView(R.id.tvNameAccountSetting)
    TextView tvNameAccountSetting;
    @BindView(R.id.tvChangeNameAccountSetting)
    TextView tvChangeNameAccountSetting;
    @BindView(R.id.tvEmailAccountSetting)
    TextView tvEmailAccountSetting;
    @BindView(R.id.tvChangeEmailAccountSetting)
    TextView tvChangeEmailAccountSetting;
    @BindView(R.id.tvPhoneAccountSetting)
    TextView tvPhoneAccountSetting;
    @BindView(R.id.tvChangePhoneAccountSetting)
    TextView tvChangePhoneAccountSetting;
    @BindView(R.id.tvPassAccountSetting)
    TextView tvPassAccountSetting;
    @BindView(R.id.tvChangePassAccountSetting)
    TextView tvChangePassAccountSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);

        setupNav("Account Setting");

        tvNameAccountSetting.setText(getIntent().getStringExtra("changeName"));
        tvEmailAccountSetting.setText(getIntent().getStringExtra("changeEmail"));
        tvPhoneAccountSetting.setText(getIntent().getStringExtra("changePhone"));
        tvPassAccountSetting.setText(getIntent().getStringExtra("changePass"));

    }

    @OnClick({R.id.tvChangeNameAccountSetting, R.id.tvChangeEmailAccountSetting, R.id.tvChangePhoneAccountSetting, R.id.tvChangePassAccountSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvChangeNameAccountSetting:
//                startActivity(new Intent(getApplicationContext(), ChangeName.class));
                Intent intent1 = new Intent(getApplicationContext(), ChangeName.class);
                String changeName = tvNameAccountSetting.getText().toString();
                intent1.putExtra("changeName", changeName);
                startActivity(intent1);
                finish();
                break;
            case R.id.tvChangeEmailAccountSetting:
//                startActivity(new Intent(getApplicationContext(), ChangeEmail.class));
                Intent intent2 = new Intent(getApplicationContext(), ChangeEmail.class);
                String changeEmail = tvChangeEmailAccountSetting.getText().toString();
                intent2.putExtra("changeEmail", changeEmail);
                startActivity(intent2);
                finish();
                break;
            case R.id.tvChangePhoneAccountSetting:
//                startActivity(new Intent(getApplicationContext(), ChangePhoneNumber.class));
                Intent intent3 = new Intent(getApplicationContext(), ChangePhoneNumber.class);
                String changePhone = tvChangePhoneAccountSetting.getText().toString();
                intent3.putExtra("changePhone", changePhone);
                startActivity(intent3);
                finish();
                break;
            case R.id.tvChangePassAccountSetting:
//                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
                Intent intent4 = new Intent(getApplicationContext(), ChangePassword.class);
                String changePass = tvChangePassAccountSetting.getText().toString();
                intent4.putExtra("changePass", changePass);
                startActivity(intent4);
                finish();
                break;
        }
    }
}
