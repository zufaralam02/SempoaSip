package com.zufaralam02.sempoasip.Parent.LoginRegister.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Adapters.AdapterAddChild;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Models.ModelAddChild;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class AddChild extends BaseActivitySempoa implements View.OnClickListener {
    RecyclerView recyclerAddChild;
    Button btnAddAddChild, btnSaveAddChild;
//    Button btnDeleteChild;
    ArrayList<ModelAddChild> modelAddChild;
    AdapterAddChild adapterAddChild;

    private final String idChild[] = {
            ""
    };
    private final String nameChild[] = {
            ""
    };
    private final String birthChild[] = {
            ""
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        setupNav("Add Child");
        initialization();
        setupWidget();
        setupRecycler();
    }

    private void initialization() {
        recyclerAddChild = findViewById(R.id.recyclerAddChild);
        btnAddAddChild = findViewById(R.id.btnAddAddChild);
        btnSaveAddChild = findViewById(R.id.btnSaveAddChild);
//        btnDeleteChild = findViewById(R.id.btnDeleteChild);
    }

    private void setupWidget() {
        btnAddAddChild.setOnClickListener(this);
        btnSaveAddChild.setOnClickListener(this);
//        btnDeleteChild.setOnClickListener(this);
    }

    private void setupRecycler() {
        modelAddChild = AddChildData();
        adapterAddChild = new AdapterAddChild(this, modelAddChild, R.layout.list_add_child);
        BaseHelper.setupRecyclerView(recyclerAddChild, adapterAddChild);
        adapterAddChild.setModelAddChild1(modelAddChild);

    }

    private ArrayList<ModelAddChild> AddChildData() {

        modelAddChild = new ArrayList<>();

        for (int i = 0; i < idChild.length; i++) {

            ModelAddChild modelAddChild1 = new ModelAddChild();
            modelAddChild1.setChildId(idChild[i]);
            modelAddChild1.setChildName(nameChild[i]);
            modelAddChild1.setChildBirth(birthChild[i]);
            modelAddChild.add(modelAddChild1);
        }

        return modelAddChild;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnAddAddChild:
                modelAddChild.add(new ModelAddChild());
                adapterAddChild.notifyDataSetChanged();
                break;

//            case R.id.btnDeleteChild:
//                if (modelAddChild.isEmpty()) {
//                    return;
//                } else {
//                    modelAddChild.remove(modelAddChild.size() - 1);
//                    adapterAddChild.notifyDataSetChanged();
//                    break;
//                }

            case R.id.btnSaveAddChild:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
        }
    }

}
