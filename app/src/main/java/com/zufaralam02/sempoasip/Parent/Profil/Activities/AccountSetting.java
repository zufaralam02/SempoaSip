package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import org.json.JSONException;
import org.json.JSONObject;

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

    String resultName, resultEmail, resultHp, resultPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        ButterKnife.bind(this);

        setupNav("Account Setting");

        resultName = getIntent().getStringExtra("resultName");
        resultEmail = getIntent().getStringExtra("resultEmail");
        resultHp = getIntent().getStringExtra("resultHp");
        resultPwd = getIntent().getStringExtra("resultPwd");

        tvNameAccountSetting.setText(resultName);
        tvEmailAccountSetting.setText(resultEmail);
        tvPhoneAccountSetting.setText(resultHp);
        tvPassAccountSetting.setText(resultPwd);


//        Bundle extras = getIntent().getExtras();
//        if (extras != null)
//            resultNama = extras.getString("result_nama");
//        tvResultNama.setText(resultNama);

    }

    @OnClick({R.id.tvChangeNameAccountSetting, R.id.tvChangeEmailAccountSetting, R.id.tvChangePhoneAccountSetting, R.id.tvChangePassAccountSetting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvChangeNameAccountSetting:
                startActivity(new Intent(getApplicationContext(), ChangeName.class));
                Intent intent1 = new Intent(getApplicationContext(), ChangeName.class);
                intent1.putExtra("resultName", resultName);
                intent1.putExtra("resultPwd", resultPwd);
                startActivity(intent1);
//                finish();
                break;
            case R.id.tvChangeEmailAccountSetting:
                startActivity(new Intent(getApplicationContext(), ChangeEmail.class));
                Intent intent2 = new Intent(getApplicationContext(), ChangeEmail.class);
                intent2.putExtra("resultEmail", resultEmail);
                intent2.putExtra("resultPwd", resultPwd);
                startActivity(intent2);
//                finish();
                break;
            case R.id.tvChangePhoneAccountSetting:
                startActivity(new Intent(getApplicationContext(), ChangePhoneNumber.class));
                Intent intent3 = new Intent(getApplicationContext(), ChangePhoneNumber.class);
                intent3.putExtra("resultHp", resultHp);
                intent3.putExtra("resultPwd", resultPwd);
                startActivity(intent3);
//                finish();
                break;
            case R.id.tvChangePassAccountSetting:
                startActivity(new Intent(getApplicationContext(), ChangePassword.class));
                Intent intent4 = new Intent(getApplicationContext(), ChangePassword.class);
                intent4.putExtra("resultPwd", resultPwd);
                startActivity(intent4);
//                finish();
                break;
        }
    }
}
