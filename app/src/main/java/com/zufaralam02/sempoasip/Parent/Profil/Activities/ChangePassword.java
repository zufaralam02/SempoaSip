package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePassword extends BaseActivitySempoa {

    @BindView(R.id.edtCurrentPassChangePass)
    TextInputEditText edtCurrentPassChangePass;
    @BindView(R.id.edtNewPassChangePass)
    TextInputEditText edtNewPassChangePass;
    @BindView(R.id.btnSaveChangePass)
    Button btnSaveChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        setupNav("Change Password");

    }

    @OnClick(R.id.btnSaveChangePass)
    public void onClick() {
        startActivity(new Intent(getApplicationContext(), AccountSetting.class));
    }
}
