package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class ChangeEmail extends BaseActivitySempoa {
    Button btnSaveChangeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        setupNav("Change Email");
        initialization();
    }

    private void initialization() {
        btnSaveChangeEmail = findViewById(R.id.btnSaveChangeEmail);
        btnSaveChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
