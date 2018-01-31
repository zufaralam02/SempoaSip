package com.zufaralam02.sempoasip.Parent.Notification.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.zufaralam02.sempoasip.Parent.Notification.Activities.DetailNotification;
import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotification;
import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotificationn;
import com.zufaralam02.sempoasip.Parent.Notification.Models.Result;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user on 18/01/2018.
 */

public class AdapterNotification extends BaseRecyclerAdapter {

//    private ArrayList<Result> result;
//
//    public AdapterNotification(ArrayList<Result> result) {
//        this.result = result;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = layoutInflater.inflate(R.layout.list_notification, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.tvTitleNotification.setText(result.get(position).getNotificationTitle());
//        holder.tvDetailNotification.setText(result.get(position).getNotificationContent());
//        holder.tvTimeNotification.setText(result.get(position).getNotificationCreated());
//    }
//
//    @Override
//    public int getItemCount() {
//        return result.size();
//    }
//
//    @OnClick(R.id.linearNotif)
//    public void onClick() {
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.tvTitleNotification)
//        TextView tvTitleNotification;
//        @BindView(R.id.tvDetailNotification)
//        TextView tvDetailNotification;
//        @BindView(R.id.tvTimeNotification)
//        TextView tvTimeNotification;
//        @BindView(R.id.ivNotification)
//        ImageView ivNotification;
//        @BindView(R.id.linearNotif)
//        LinearLayout linearNotif;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//        }
//    }


    public ArrayList<ModelNotification> getModelNotification() {
        return modelNotification;
    }

    public void setModelNotification(ArrayList<ModelNotification> modelNotification) {
        this.modelNotification = modelNotification;
    }

    ArrayList<ModelNotification> modelNotification;

    public AdapterNotification(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterNotification.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, final int position) {

        AdapterNotification.Holder holder = (AdapterNotification.Holder) objectHolder;
        final ModelNotification modelNotification = (ModelNotification) getItem(position);

        holder.tvTitleNotification.setText(modelNotification.getTitleNotif());
        holder.tvDetailNotification.setText(modelNotification.getDetailNotif());
        holder.tvTimeNotification.setText(modelNotification.getTimeNotif());
//        holder.ivNotification.setImageResource(modelNotification.getImageNotif());
        Picasso.with(getContext()).load(modelNotification.getImageNotif()).into(holder.ivNotification);

        if (modelNotification.isReaded()) {
            holder.tvTitleNotification.setTextColor(Color.parseColor("#000000"));
            holder.tvDetailNotification.setTextColor(Color.parseColor("#000000"));
        } else {
            holder.tvTitleNotification.setTextColor(Color.parseColor("#fdb17e"));
            holder.tvDetailNotification.setTextColor(Color.parseColor("#fdb17e"));
        }

        holder.linearNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailNotification.class);
//                ModelNotification modelNotification1 = modelNotification.get

                intent.putExtra("titleNotif", modelNotification.getTitleNotif());
                intent.putExtra("timeNotif", modelNotification.getTimeNotif());
                intent.putExtra("detailNotif", modelNotification.getDetailNotif());
                intent.putExtra("imageNotif", modelNotification.getImageNotif());

//                Bundle bundle = new Bundle();
//                bundle.putString("titleNotif", String.valueOf(modelNotification.getTitleNotif()));
//                bundle.putString("timeNotif", String.valueOf(modelNotification.getTimeNotif()));
//                bundle.putString("detailNotif", String.valueOf(modelNotification.getDetailNotif()));
//                bundle.putString("imageNotif", String.valueOf(modelNotification.getImageNotif()));

//                String title = intent.getExtras().getString("titleNotif");
//                String time = intent.getExtras().getString("timeNotif");
//                String detail = intent.getExtras().getString("detailNotif");
//                int image = intent.getExtras().getInt("imageNotif");
//
//                if (title != null && time != null && detail != null) {
//                    DetailNotification detailNotification = new DetailNotification();
//                    detailNotification.getDetail(title, time, detail);
//                }

                getContext().startActivity(intent);
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
        TextView tvTitleNotification, tvDetailNotification, tvTimeNotification;
        ImageView ivNotification;
        LinearLayout linearNotif;

        public Holder(View v) {
            super(v);

            tvTitleNotification = v.findViewById(R.id.tvTitleNotification);
            tvDetailNotification = v.findViewById(R.id.tvDetailNotification);
            tvTimeNotification = v.findViewById(R.id.tvTimeNotification);
            ivNotification = v.findViewById(R.id.ivNotification);
            linearNotif = v.findViewById(R.id.linearNotif);

        }
    }
}
