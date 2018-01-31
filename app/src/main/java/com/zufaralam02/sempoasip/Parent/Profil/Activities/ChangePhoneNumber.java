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

public class ChangePhoneNumber extends BaseActivitySempoa {

    @BindView(R.id.tvPhoneChangePhone)
    TextView tvPhoneChangePhone;
    @BindView(R.id.edtPhoneChangePhone)
    TextInputEditText edtPhoneChangePhone;
    @BindView(R.id.edtPassChangePhone)
    TextInputEditText edtPassChangePhone;
    @BindView(R.id.btnSaveChangePhone)
    Button btnSaveChangePhone;

    String resultHp, resultPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        ButterKnife.bind(this);

        setupNav("Change Phone Number");
        resultPwd = getIntent().getStringExtra("resultPwd");
        resultHp = getIntent().getStringExtra("resultHp");
        tvPhoneChangePhone.setText(resultHp);

    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btnSaveChangePhone)
    public void onClick() {
        String resultPwd2 = edtPassChangePhone.getText().toString();
        if (resultHp.isEmpty()) {
            Toast.makeText(this, "Hp Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (resultPwd2.isEmpty()) {
            Toast.makeText(this, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else {
            if (Objects.equals(resultPwd, resultPwd2)) {
                Intent intent = new Intent(getApplicationContext(), AccountSetting.class);
                String resultHp = edtPhoneChangePhone.getText().toString();
                intent.putExtra("resultHp", resultHp);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
