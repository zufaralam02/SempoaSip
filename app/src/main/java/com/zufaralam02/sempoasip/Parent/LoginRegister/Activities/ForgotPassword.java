package com.zufaralam02.sempoasip.Parent.LoginRegister.Activities;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class ForgotPassword extends BaseActivitySempoa implements View.OnClickListener {
    EditText edtPassForgot;
    Button btnResetForgot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        setupNav("Forgot Password");
        initialization();
        setUpWidget();

    }

    private void initialization() {
        edtPassForgot = findViewById(R.id.edtPassForgot);
        btnResetForgot = findViewById(R.id.btnResetForgot);
    }

    private void setUpWidget() {
        btnResetForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnResetForgot:
//                String edtPass = edtPassForgot.getText().toString();
//                if (edtPass.isEmpty()) {

//                } else {
                customDialogForgot();
//                }
                break;
        }

    }

    private void customDialogForgot() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_forgot, null);
        builder.setView(view);
        builder.setCancelable(false);

        view.findViewById(R.id.btnOkForgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
        builder.show();
    }

}
