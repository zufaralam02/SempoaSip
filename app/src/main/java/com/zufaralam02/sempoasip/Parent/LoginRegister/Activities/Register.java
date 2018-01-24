package com.zufaralam02.sempoasip.Parent.LoginRegister.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Adapters.AdapterAddChild;
import com.zufaralam02.sempoasip.R;

public class Register extends BaseActivitySempoa implements View.OnClickListener {
    EditText edtNameRegister, edtEmailRegister, edtPhoneRegister, edtPassRegister;
    Button btnRegisterRegister;
    TextView tvLoginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupNav("Register (Parent Only)");
        initialization();
        setUpWidget();
    }

    private void initialization() {
        edtNameRegister = findViewById(R.id.edtNameRegister);
        edtEmailRegister = findViewById(R.id.edtEmailRegister);
        edtPhoneRegister = findViewById(R.id.edtPhoneRegister);
        edtPassRegister = findViewById(R.id.edtPassLogin);
        btnRegisterRegister = findViewById(R.id.btnRegisterRegister);
        tvLoginRegister = findViewById(R.id.tvLoginRegister);
    }

    private void setUpWidget() {
        btnRegisterRegister.setOnClickListener(this);
        tvLoginRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnRegisterRegister:
                startActivity(new Intent(getApplicationContext(), AddChild.class));
                finish();
                break;
            case R.id.tvLoginRegister:
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
                break;
        }

    }
}
