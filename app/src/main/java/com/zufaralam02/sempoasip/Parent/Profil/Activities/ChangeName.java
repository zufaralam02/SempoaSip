package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class ChangeName extends BaseActivitySempoa {
    Button btnSaveChangeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);

        setupNav("Change Name");
        initialization();
    }

    private void initialization() {
        btnSaveChangeName = findViewById(R.id.btnSaveChangeName);
        btnSaveChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
