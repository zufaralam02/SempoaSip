package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import java.util.Objects;

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

    String resultEmail, resultPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        ButterKnife.bind(this);

        setupNav("Change Email");
        resultPwd = getIntent().getStringExtra("resultPwd");
        resultEmail = getIntent().getStringExtra("resultEmail");
        tvEmailChangeEmail.setText(resultEmail);

    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btnSaveChangeEmail)
    public void onClick() {
        String resultPwd2 = edtPassChangeEmail.getText().toString();
        if (resultEmail.isEmpty()) {
            Toast.makeText(this, "Email Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (resultPwd2.isEmpty()) {
            Toast.makeText(this, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else {
            if (Objects.equals(resultPwd, resultPwd2)) {
                Intent intent = new Intent(getApplicationContext(), AccountSetting.class);
                String resultEmail = edtEmailChangeEmail.getText().toString();
                intent.putExtra("resultEmail", resultEmail);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
