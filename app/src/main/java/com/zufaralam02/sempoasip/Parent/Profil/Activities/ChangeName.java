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

public class ChangeName extends BaseActivitySempoa {

    @BindView(R.id.tvNameChangeName)
    TextView tvNameChangeName;
    @BindView(R.id.edtNameChangeName)
    TextInputEditText edtNameChangeName;
    @BindView(R.id.edtPassChangeName)
    TextInputEditText edtPassChangeName;
    @BindView(R.id.btnSaveChangeName)
    Button btnSaveChangeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_name);
        ButterKnife.bind(this);

        setupNav("Change Name");
        tvNameChangeName.setText(getIntent().getStringExtra("changeName"));

    }

    @OnClick(R.id.btnSaveChangeName)
    public void onClick() {
//        startActivity(new Intent(getApplicationContext(), AccountSetting.class));
        Intent intent = new Intent(getApplicationContext(), AccountSetting.class);
        String changeName = edtNameChangeName.getText().toString();
        intent.putExtra("changeName", changeName);
        startActivity(intent);
        finish();
    }

}
