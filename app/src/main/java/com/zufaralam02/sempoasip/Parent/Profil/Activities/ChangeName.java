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

public class ChangeName extends BaseActivitySempoa {

    @BindView(R.id.tvNameChangeName)
    TextView tvNameChangeName;
    @BindView(R.id.edtNameChangeName)
    TextInputEditText edtNameChangeName;
    @BindView(R.id.edtPassChangeName)
    TextInputEditText edtPassChangeName;
    @BindView(R.id.btnSaveChangeName)
    Button btnSaveChangeName;

    String resultName, resultPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ButterKnife.bind(this);

        setupNav("Change Name");
        resultPwd = getIntent().getStringExtra("resultPwd");
        resultName = getIntent().getStringExtra("resultName");
        tvNameChangeName.setText(resultName);
    }

    @SuppressLint("NewApi")
    @OnClick(R.id.btnSaveChangeName)
    public void onClick() {
        String resultPwd2 = edtPassChangeName.getText().toString();
        if (resultName.isEmpty()) {
            Toast.makeText(this, "Nama Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (resultPwd2.isEmpty()) {
            Toast.makeText(this, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else {
            if (Objects.equals(resultPwd, resultPwd2)) {
                Intent intent = new Intent(getApplicationContext(), AccountSetting.class);
                String resultName = edtNameChangeName.getText().toString();
                intent.putExtra("resultName", resultName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Password Salah", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
