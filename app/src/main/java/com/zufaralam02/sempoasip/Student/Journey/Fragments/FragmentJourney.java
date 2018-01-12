package com.zufaralam02.sempoasip.Student.Journey.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Student.Journey.Adapters.AdapterJourney;
import com.zufaralam02.sempoasip.Student.Journey.Models.ModelJourney;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentJourney extends Fragment {
    RecyclerView recyclerViewJourney;

    public FragmentJourney() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_journey, container, false);

        recyclerViewJourney = (RecyclerView) view.findViewById(R.id.recyclerViewJourney);
        recyclerViewJourney.setFocusable(false);
        ArrayList<ModelJourney> modelJourney = journeyData();
        AdapterJourney adapterJourney = new AdapterJourney(getActivity(), modelJourney, R.layout.list_journey);
        BaseHelper.setupRecyclerView(recyclerViewJourney, adapterJourney);
        ViewCompat.setNestedScrollingEnabled(recyclerViewJourney, false);
//        adapterJourney.notifyDataSetChanged();

        return view;
    }

    private ArrayList<ModelJourney> journeyData() {
        ArrayList<ModelJourney> modelJourney = new ArrayList<>();

        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(true));
        modelJourney.add(new ModelJourney(false));
        modelJourney.add(new ModelJourney(false));
        modelJourney.add(new ModelJourney(false));
        modelJourney.add(new ModelJourney(false));
        modelJourney.add(new ModelJourney(false));

        return modelJourney;
    }
}
