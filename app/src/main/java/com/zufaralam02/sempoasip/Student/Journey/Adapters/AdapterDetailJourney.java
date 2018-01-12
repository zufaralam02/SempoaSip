package com.zufaralam02.sempoasip.Student.Journey.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.squareup.picasso.Picasso;
import com.zufaralam02.sempoasip.Student.Journey.Models.ModelDetailJourney;
import com.zufaralam02.sempoasip.R;

import java.util.List;

/**
 * Created by user on 14/12/2017.
 */

public abstract class AdapterDetailJourney extends BaseRecyclerAdapter {
    RelativeLayout rlDetailJourneyLayout;

    public RelativeLayout getRlDetailJourneyLayout() {
        return rlDetailJourneyLayout;
    }

    public void setRlDetailJourneyLayout(RelativeLayout rlDetailJourneyLayout) {
        this.rlDetailJourneyLayout = rlDetailJourneyLayout;
    }

    int positionJourney;

    public int getPositionJourney() {
        return positionJourney;
    }

    public void setPositionJourney(int positionJourney) {
        this.positionJourney = positionJourney;
    }

    public AdapterDetailJourney(Context context, List<?> items, int cellLayout) {
        super(context, items, cellLayout);
    }

    @Override
    public RecyclerView.ViewHolder objectHolder(View v) {
        return new Holder(v);
    }

    @Override
    public RecyclerView.ViewHolder headerHolder(View v) {
        return null;
    }

    @Override
    public void setView(RecyclerView.ViewHolder objectHolder, final int position) {
        Holder holder = (Holder) objectHolder;
        ModelDetailJourney modelDetailJourney = (ModelDetailJourney) getItem(position);

//        holder.imageDetailJourney1.setImageResource(modelDetailJourney.getImageDetailJourney1());
//        holder.imageDetailJourney2.setImageResource(modelDetailJourney.getImageDetailJourney2());
//        holder.textDetailJourney1.setText(modelDetailJourney.getTextDetailJourney1());
//        holder.textDetailJourney2.setText(modelDetailJourney.getTextDetailJourney2());
        holder.textDetailJourney1.setText(modelDetailJourney.getTextDetailJourney());
        holder.textDetailJourney2.setText(modelDetailJourney.getTextDetailJourney());

        holder.textDetailJourney1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelected(position);
            }
        });
        holder.textDetailJourney2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelected(position);
            }
        });

        switch (positionJourney) {
            case 0:
                setupView(R.color.bg_journey_details_1, R.drawable.item_1_1_1, R.drawable.item_1_1_2,
                        R.drawable.item_1_2_1, R.drawable.item_1_2_2, R.drawable.item_1_3_1, R.drawable.item_1_3_2,
                        R.drawable.item_1_4_1, R.drawable.item_1_4_2, position, holder, modelDetailJourney);
                break;
            case 1:
                setupView(R.color.bg_journey_details_2, R.drawable.item_2_1_1, R.drawable.item_2_1_2,
                        R.drawable.item_2_2_1, R.drawable.item_2_2_2, R.drawable.item_2_3_1, R.drawable.item_2_3_2,
                        R.drawable.item_2_4_1, R.drawable.item_2_4_2, position, holder, modelDetailJourney);
                break;
            case 2:
                setupView(R.color.bg_journey_details_3, R.drawable.item_3_1_1, R.drawable.item_3_1_2,
                        R.drawable.item_3_2_1, R.drawable.item_3_2_2, R.drawable.item_3_3_1, R.drawable.item_3_3_2,
                        R.drawable.item_3_4_1, R.drawable.item_3_4_2, position, holder, modelDetailJourney);
                break;
            case 3:
                setupView(R.color.bg_journey_details_4, R.drawable.item_4_1_1, R.drawable.item_4_1_2,
                        R.drawable.item_4_2_1, R.drawable.item_4_2_2, R.drawable.item_4_3_1, R.drawable.item_4_3_2,
                        R.drawable.item_4_4_1, R.drawable.item_4_4_2, position, holder, modelDetailJourney);
                break;
            case 4:
                setupView(R.color.bg_journey_details_5, R.drawable.item_5_1_1, R.drawable.item_5_1_2,
                        R.drawable.item_5_2_1, R.drawable.item_5_2_2, R.drawable.item_5_3_1, R.drawable.item_5_3_2,
                        R.drawable.item_5_4_1, R.drawable.item_5_4_2, position, holder, modelDetailJourney);
                break;
            case 5:
                setupView(R.color.bg_journey_details_6, R.drawable.item_6_1_1, R.drawable.item_6_1_2,
                        R.drawable.item_6_2_1, R.drawable.item_6_2_2, R.drawable.item_6_3_1, R.drawable.item_6_3_2,
                        R.drawable.item_6_4_1, R.drawable.item_6_4_2, position, holder, modelDetailJourney);
                break;
            case 6:
                setupView(R.color.bg_journey_details_7, R.drawable.item_7_1_1, R.drawable.item_7_1_2,
                        R.drawable.item_7_2_1, R.drawable.item_7_2_2, R.drawable.item_7_3_1, R.drawable.item_7_3_2,
                        R.drawable.item_7_4_1, R.drawable.item_7_4_2, position, holder, modelDetailJourney);
                break;
            case 7:
                setupView(R.color.bg_journey_details_8, R.drawable.item_8_1_1, R.drawable.item_8_1_2,
                        R.drawable.item_8_2_1, R.drawable.item_8_2_2, R.drawable.item_8_3_1, R.drawable.item_8_3_2,
                        R.drawable.item_8_4_1, R.drawable.item_8_4_2, position, holder, modelDetailJourney);
                break;
            case 8:
                setupView(R.color.bg_journey_details_9, R.drawable.item_9_1_1, R.drawable.item_9_1_2,
                        R.drawable.item_9_2_1, R.drawable.item_9_2_2, R.drawable.item_9_3_1, R.drawable.item_9_3_2,
                        R.drawable.item_9_4_1, R.drawable.item_9_4_2, position, holder, modelDetailJourney);
                break;
            case 9:
                setupView(R.color.bg_journey_details_10, R.drawable.item_10_1_1, R.drawable.item_10_1_2,
                        R.drawable.item_10_2_1, R.drawable.item_10_2_2, R.drawable.item_10_3_1, R.drawable.item_10_3_2,
                        R.drawable.item_10_4_1, R.drawable.item_10_4_2, position, holder, modelDetailJourney);
                break;
            case 10:
                setupView(R.color.bg_journey_details_11, R.drawable.item_11_1_1, R.drawable.item_11_1_2,
                        R.drawable.item_11_2_1, R.drawable.item_11_2_2, R.drawable.item_11_3_1, R.drawable.item_11_3_2,
                        R.drawable.item_11_4_1, R.drawable.item_11_4_2, position, holder, modelDetailJourney);
                break;
        }

    }

    public void setupView(int background, int image1, int image2, int image3, int image4, int image5,
                          int image6, int image7, int image8, int position, Holder holder,
                          ModelDetailJourney modelDetailJourney) {

        rlDetailJourneyLayout.setBackgroundResource(background);

        if (position == 0) {
//            holder.imageDetailJourney1.setImageResource(image1);
//            holder.imageDetailJourney2.setImageResource(image2);
            Picasso.with(getContext()).load(image1).into(holder.imageDetailJourney1);
            Picasso.with(getContext()).load(image2).into(holder.imageDetailJourney2);
            holder.textDetailJourney1.setVisibility(View.VISIBLE);
            holder.imageLock1.setVisibility(View.VISIBLE);
            holder.textDetailJourney2.setVisibility(View.INVISIBLE);
            holder.imageLock2.setVisibility(View.INVISIBLE);
//            holder.textDetailJourney1.setEnabled(true);
            if (modelDetailJourney.isAvailabe()) {
                holder.textDetailJourney1.setVisibility(View.VISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            } else {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.VISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            }
        } else if (position == getItemCount() - 1) {
//            holder.imageDetailJourney1.setImageResource(image7);
//            holder.imageDetailJourney2.setImageResource(image8);
            Picasso.with(getContext()).load(image7).into(holder.imageDetailJourney1);
            Picasso.with(getContext()).load(image8).into(holder.imageDetailJourney2);
            holder.textDetailJourney1.setVisibility(View.INVISIBLE);
            holder.imageLock1.setVisibility(View.INVISIBLE);
            holder.textDetailJourney2.setVisibility(View.VISIBLE);
            holder.imageLock2.setVisibility(View.VISIBLE);
            if (modelDetailJourney.isAvailabe()) {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.VISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            } else {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.VISIBLE);
            }
        } else if (position % 2 == 1) {
//            holder.imageDetailJourney1.setImageResource(image3);
//            holder.imageDetailJourney2.setImageResource(image4);
            Picasso.with(getContext()).load(image3).into(holder.imageDetailJourney1);
            Picasso.with(getContext()).load(image4).into(holder.imageDetailJourney2);
            holder.textDetailJourney1.setVisibility(View.INVISIBLE);
            holder.imageLock1.setVisibility(View.INVISIBLE);
            holder.textDetailJourney2.setVisibility(View.VISIBLE);
            holder.imageLock2.setVisibility(View.VISIBLE);
            if (modelDetailJourney.isAvailabe()) {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.VISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            } else {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.VISIBLE);
            }
        } else if (position % 2 == 0) {
//            holder.imageDetailJourney1.setImageResource(image5);
//            holder.imageDetailJourney2.setImageResource(image6);
            Picasso.with(getContext()).load(image5).into(holder.imageDetailJourney1);
            Picasso.with(getContext()).load(image6).into(holder.imageDetailJourney2);
            holder.textDetailJourney1.setVisibility(View.VISIBLE);
            holder.imageLock1.setVisibility(View.VISIBLE);
            holder.textDetailJourney2.setVisibility(View.INVISIBLE);
            holder.imageLock2.setVisibility(View.INVISIBLE);
            if (modelDetailJourney.isAvailabe()) {
                holder.textDetailJourney1.setVisibility(View.VISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.INVISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            } else {
                holder.textDetailJourney1.setVisibility(View.INVISIBLE);
                holder.textDetailJourney2.setVisibility(View.INVISIBLE);
                holder.imageLock1.setVisibility(View.VISIBLE);
                holder.imageLock2.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }
//
//    @Override
//    public void itemSelected(int position) {
//
//    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageDetailJourney1, imageDetailJourney2, imageLock1, imageLock2;
        TextView textDetailJourney1, textDetailJourney2;

        public Holder(View itemView) {
            super(itemView);

            imageDetailJourney1 = itemView.findViewById(R.id.imageDetailJourney1);
            imageDetailJourney2 = itemView.findViewById(R.id.imageDetailJourney2);
            textDetailJourney1 = itemView.findViewById(R.id.textDetailJourney1);
            textDetailJourney2 = itemView.findViewById(R.id.textDetailJourney2);
            imageLock1 = itemView.findViewById(R.id.imageLock1);
            imageLock2 = itemView.findViewById(R.id.imageLock2);
        }
    }

}
