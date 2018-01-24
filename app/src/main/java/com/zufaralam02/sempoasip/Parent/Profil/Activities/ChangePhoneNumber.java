package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.Profil.Fragments.FragmentProfilParent;
import com.zufaralam02.sempoasip.R;

public class ChangePhoneNumber extends BaseActivitySempoa implements View.OnClickListener {
    Button btnSaveChangePhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);

        setupNav("Change Phone Number");
        initialization();
    }

    private void initialization() {
        btnSaveChangePhone = findViewById(R.id.btnSaveChangePhone);
        btnSaveChangePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSavePhone:
//                startActivity(new Intent(this, FragmentProfilParent.class));
//                break;
//    }

    }
}
