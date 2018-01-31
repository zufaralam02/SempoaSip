package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.Toast;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import java.util.Objects;

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

    String resultPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);

        setupNav("Change Password");
        resultPwd = getIntent().getStringExtra("resultPwd");

    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btnSaveChangePass)
    public void onClick() {

    }
}

