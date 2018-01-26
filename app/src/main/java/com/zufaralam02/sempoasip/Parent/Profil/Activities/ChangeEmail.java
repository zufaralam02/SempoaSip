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

public class ChangeEmail extends BaseActivitySempoa {

    @BindView(R.id.tvEmailChangeEmail)
    TextView tvEmailChangeEmail;
    @BindView(R.id.edtEmailChangeEmail)
    TextInputEditText edtEmailChangeEmail;
    @BindView(R.id.edtPassChangeEmail)
    TextInputEditText edtPassChangeEmail;
    @BindView(R.id.btnSaveChangeEmail)
    Button btnSaveChangeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        ButterKnife.bind(this);

        setupNav("Change Email");

    }

    @OnClick(R.id.btnSaveChangeEmail)
    public void onClick() {
        tvEmailChangeEmail.setText(edtEmailChangeEmail.getText().toString());
        startActivity(new Intent(getApplicationContext(), AccountSetting.class));
    }
}
