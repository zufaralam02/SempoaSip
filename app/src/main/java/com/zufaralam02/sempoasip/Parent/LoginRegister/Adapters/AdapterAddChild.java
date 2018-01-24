package com.zufaralam02.sempoasip.Parent.LoginRegister.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Models.ModelAddChild;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 16/01/2018.
 */

public class AdapterAddChild extends BaseRecyclerAdapter {
    private ArrayList<ModelAddChild> modelAddChild1;

    public ArrayList<ModelAddChild> getModelAddChild1() {
        return modelAddChild1;
    }

    public void setModelAddChild1(ArrayList<ModelAddChild> modelAddChild1) {
        this.modelAddChild1 = modelAddChild1;
    }

    public AdapterAddChild(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterAddChild.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, int position) {

        final AdapterAddChild.Holder holder = (AdapterAddChild.Holder) objectHolder;
        final ModelAddChild modelAddChild = (ModelAddChild) getItem(position);

        holder.edtIdAddChild.setText(modelAddChild.getChildId());
        holder.edtNameAddChild.setText(modelAddChild.getChildName());
        holder.edtBirthAddChild.setText(modelAddChild.getChildBirth());

        holder.btnDeleteAddChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modelAddChild1.isEmpty()) {
                    return;
                } else {
                    modelAddChild1.remove(modelAddChild1.size() - 1);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    private class Holder extends RecyclerView.ViewHolder {
        EditText edtIdAddChild, edtNameAddChild, edtBirthAddChild;
        Button btnDeleteAddChild;

        public Holder(View v) {
            super(v);

            edtIdAddChild = v.findViewById(R.id.edtIdAddChild);
            edtNameAddChild = v.findViewById(R.id.edtNameAddChild);
            edtBirthAddChild = v.findViewById(R.id.edtBirthAddChild);
            btnDeleteAddChild = v.findViewById(R.id.btnDeleteAddChild);
        }
    }
}
