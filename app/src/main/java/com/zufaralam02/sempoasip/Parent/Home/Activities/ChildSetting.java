package com.zufaralam02.sempoasip.Parent.Home.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.Home.Adapters.AdapterChildSetting;
import com.zufaralam02.sempoasip.Parent.Home.Models.ModelChildSetting;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Activities.AddChild;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class ChildSetting extends BaseActivitySempoa implements View.OnClickListener {
    RecyclerView recyclerChildSetting;
    Button btnAddChildSetting, btnGoToHome;
    ArrayList<ModelChildSetting> modelChildSetting;
    AdapterChildSetting adapterChildSetting;

    private final String childName[] = {
            "Middleston Henry"
    };
    private final String childNumber[] = {
            "11121300"
    };
    private final String childPlace[] = {
            "Pejaten Village"
    };
    private final String childTitlePass[] = {
            "Password for Middleston"
    };
    private final String childPass[] = {
            "Mid123"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_setting);

        setupNav("Child Setting");
        initialization();
        setupWidget();
        setupRecycler();
    }

    private void initialization() {
        recyclerChildSetting = findViewById(R.id.recyclerChildSetting);
        btnAddChildSetting = findViewById(R.id.btnAddChildSetting);
        btnGoToHome = findViewById(R.id.btnGoToHome);
    }

    private void setupWidget() {
        btnAddChildSetting.setOnClickListener(this);
        btnGoToHome.setOnClickListener(this);

    }

    private void setupRecycler() {
        modelChildSetting = AddChildSettingData();
        adapterChildSetting = new AdapterChildSetting(this, modelChildSetting, R.layout.list_child_setting);
        BaseHelper.setupRecyclerView(recyclerChildSetting, adapterChildSetting);
        adapterChildSetting.setModelChildSetting1(modelChildSetting);

    }

    private ArrayList<ModelChildSetting> AddChildSettingData() {
        modelChildSetting = new ArrayList<>();

        for (int i = 0; i < childName.length; i++) {

            ModelChildSetting modelChildSetting1 = new ModelChildSetting();
            modelChildSetting1.setChildName(childName[i]);
            modelChildSetting1.setChildNumber(childNumber[i]);
            modelChildSetting1.setChildPlace(childPlace[i]);
            modelChildSetting1.setChildTitlePass(childTitlePass[i]);
            modelChildSetting1.setChildPass(childPass[i]);
            modelChildSetting.add(modelChildSetting1);
        }

        return modelChildSetting;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnGoToHome:
//                startActivity(new Intent(this, FragmentHomeParent.class));
                finish();
                break;
            case R.id.btnAddChildSetting:
                startActivity(new Intent(this, AddChild.class));
                break;
        }

    }
}
