package com.zufaralam02.sempoasip.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.iapps.adapters.BaseRecyclerAdapter;
import com.zufaralam02.sempoasip.Activities.DetailJourney;
import com.zufaralam02.sempoasip.Models.ModelJourney;
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

//        holder.textJourneyKiri.getLayoutParams().height = holder.imageJourney.getLayoutParams().height;
        holder.imageJourney.setImageResource(modelJourney.getImageJourney());
        if (position % 2 == 0) {
            holder.textJourneyKiri.setVisibility(View.VISIBLE);
            holder.textJourneyKanan.setVisibility(View.INVISIBLE);
            holder.textJourneyKiri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailJourney.class);
                    intent.putExtra("journeyPosition", position);
                    v.getContext().startActivity(intent);
                }
            });
        } else {
            holder.textJourneyKiri.setVisibility(View.INVISIBLE);
            holder.textJourneyKanan.setVisibility(View.VISIBLE);
            holder.textJourneyKanan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailJourney.class);
                    intent.putExtra("journeyPosition", position);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }

    @Override
    public void setHeaderView(RecyclerView.ViewHolder objectHolder) {

    }

    @Override
    public void itemSelected(int position) {

    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textJourneyKiri, textJourneyKanan;
        ImageView imageJourney;

        public Holder(View itemView) {
            super(itemView);

            textJourneyKiri = itemView.findViewById(R.id.textJourneyKiri);
            textJourneyKanan = itemView.findViewById(R.id.textJourneyKanan);
            imageJourney = itemView.findViewById(R.id.imageJourney);

        }
    }
}
