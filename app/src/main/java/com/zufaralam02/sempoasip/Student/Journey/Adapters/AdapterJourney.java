package com.zufaralam02.sempoasip.Student.Journey.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.zufaralam02.sempoasip.Student.Journey.Activities.DetailJourney;
import com.zufaralam02.sempoasip.Student.Journey.Models.ModelJourney;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 14/12/2017.
 */
public class AdapterJourney extends BaseRecyclerAdapter {

    public AdapterJourney(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new AdapterJourney.Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, final int position) {

        final Holder holder = (AdapterJourney.Holder) objectHolder;
        ModelJourney modelJourney = (ModelJourney) getItem(position);

//        holder.ivJourney1.setImageResource(modelJourney.getIvJourney());
//        holder.ivJourney2.setImageResource(modelJourney.getIvJourney());

        if (position % 2 == 0) {
            holder.ivJourney1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailJourney.class);
                    intent.putExtra("journeyPosition", position);
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            holder.ivJourney2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailJourney.class);
                    intent.putExtra("journeyPosition", position);
                    v.getContext().startActivity(intent);
                }
            });
        }

        switch (position) {
            case 0:
                setupView(R.drawable.ic_journey_1_1, R.drawable.ic_journey_1_2, R.drawable.ic_journey_1_1_inactive,
                        R.drawable.ic_journey_1_2_inactive, position, holder, modelJourney);
                break;
            case 1:
                setupView(R.drawable.ic_journey_2_1, R.drawable.ic_journey_2_2, R.drawable.ic_journey_2_1_inactive,
                        R.drawable.ic_journey_2_2_inactive, position, holder, modelJourney);
                break;
            case 2:
                setupView(R.drawable.ic_journey_3_1, R.drawable.ic_journey_3_2, R.drawable.ic_journey_3_1_inactive,
                        R.drawable.ic_journey_3_2_inactive, position, holder, modelJourney);
                break;
            case 3:
                setupView(R.drawable.ic_journey_4_1, R.drawable.ic_journey_4_2, R.drawable.ic_journey_4_1_inactive,
                        R.drawable.ic_journey_4_2_inactive, position, holder, modelJourney);
                break;
            case 4:
                setupView(R.drawable.ic_journey_5_1, R.drawable.ic_journey_5_2, R.drawable.ic_journey_5_1_inactive,
                        R.drawable.ic_journey_5_2_inactive, position, holder, modelJourney);
                break;
            case 5:
                setupView(R.drawable.ic_journey_6_1, R.drawable.ic_journey_6_2, R.drawable.ic_journey_6_1_inactive,
                        R.drawable.ic_journey_6_2_inactive, position, holder, modelJourney);
                break;
            case 6:
                setupView(R.drawable.ic_journey_7_1, R.drawable.ic_journey_7_2, R.drawable.ic_journey_7_1_inactive,
                        R.drawable.ic_journey_7_2_inactive, position, holder, modelJourney);
                break;
            case 7:
                setupView(R.drawable.ic_journey_8_1, R.drawable.ic_journey_8_2, R.drawable.ic_journey_8_1_inactive,
                        R.drawable.ic_journey_8_2_inactive, position, holder, modelJourney);
                break;
            case 8:
                setupView(R.drawable.ic_journey_9_1, R.drawable.ic_journey_9_2, R.drawable.ic_journey_9_1_inactive,
                        R.drawable.ic_journey_9_2_inactive, position, holder, modelJourney);
                break;
            case 9:
                setupView(R.drawable.ic_journey_10_1, R.drawable.ic_journey_10_2, R.drawable.ic_journey_10_1_inactive,
                        R.drawable.ic_journey_10_2_inactive, position, holder, modelJourney);
                break;
            case 10:
                setupView(R.drawable.ic_journey_11_1, R.drawable.ic_journey_11_2, R.drawable.ic_journey_11_1_inactive,
                        R.drawable.ic_journey_11_2_inactive, position, holder, modelJourney);
                break;
        }
    }

    public void setupView(int image1, int image2, int image1Inactive, int image2Inactive, int position,
                          AdapterJourney.Holder holder,
                          ModelJourney modelJourney) {

//        holder.ivJourney1.setImageResource(image1);
//        holder.ivJourney1.setImageDrawable(ContextCompat.getDrawable(getContext(), image1));
//        holder.ivJourney2.setImageDrawable(ContextCompat.getDrawable(getContext(), image2));
//        Picasso.with(getContext()).load(image1).into(holder.ivJourney1);
//        Picasso.with(getContext()).load(image2).into(holder.ivJourney2);
        Picasso.with(getContext()).cancelRequest(holder.ivJourney1);
        Picasso.with(getContext()).cancelRequest(holder.ivJourney2);
        if (!modelJourney.isAvailable()) {
//            holder.ivJourney1.setImageResource(image1Inactive);
//            holder.ivJourney2.setImageResource(image2Inactive);
            Picasso.with(getContext()).load(image1Inactive).into(holder.ivJourney1);
            Picasso.with(getContext()).load(image2Inactive).into(holder.ivJourney2);

            holder.ivJourney1.setEnabled(false);
            holder.ivJourney2.setEnabled(false);
        } else {
//            holder.ivJourney1.setImageResource(image1);
//            holder.ivJourney2.setImageResource(image2);
            Picasso.with(getContext()).load(image1).into(holder.ivJourney1);
            Picasso.with(getContext()).load(image2).into(holder.ivJourney2);
            holder.ivJourney1.setEnabled(true);
            holder.ivJourney2.setEnabled(true);
        }
    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView ivJourney1, ivJourney2;

        public Holder(View itemView) {
            super(itemView);

            ivJourney1 = (ImageView) itemView.findViewById(R.id.ivJourney1);
            ivJourney2 = (ImageView) itemView.findViewById(R.id.ivJourney2);
        }
    }
}
