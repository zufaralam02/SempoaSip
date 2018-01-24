package com.zufaralam02.sempoasip.Parent.LoginRegister.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.BottomNavigation.BottomNavigationParent;
import com.zufaralam02.sempoasip.R;

public class Login extends BaseActivitySempoa implements View.OnClickListener {
    TextView tvForgotPassword;
    EditText edtEmailLogin, edtPassLogin;
    Button btnLoginLogin, btnRegisterLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialization();
        setUpWidget();

    }

    private void initialization() {
        tvForgotPassword = findViewById(R.id.tvForgotPassword);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassLogin = findViewById(R.id.edtPassLogin);
        btnLoginLogin = findViewById(R.id.btnLoginLogin);
        btnRegisterLogin = findViewById(R.id.btnRegisterLogin);
    }

    private void setUpWidget() {
        tvForgotPassword.setOnClickListener(this);
        btnLoginLogin.setOnClickListener(this);
        btnRegisterLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tvForgotPassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
            case R.id.btnLoginLogin:
//                String edtEmail = edtEmailLogin.getText().toString();
//                String edtPass = edtPassLogin.getText().toString();
//                if (edtEmail.isEmpty()) {
//
//                } else if (edtPass.isEmpty()) {
//
//                } else {
                startActivity(new Intent(this, BottomNavigationParent.class));
                finish();
//                }
                break;
            case R.id.btnRegisterLogin:
                startActivity(new Intent(this, Register.class));

        }


    }
}
