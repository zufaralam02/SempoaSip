package com.zufaralam02.sempoasip.Student.Challenge.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Student.Challenge.Adapters.AdapterChallenge;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelChallenge;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChallenge extends Fragment {
    RecyclerView recyclerViewChallenge;

    public FragmentChallenge() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_challenge, container, false);

        recyclerViewChallenge = (RecyclerView) view.findViewById(R.id.recyclerViewChallenge);
        recyclerViewChallenge.setFocusable(false);
        ArrayList<ModelChallenge> modelChallenge = challangeData();
        AdapterChallenge adapterChallenge = new AdapterChallenge(getActivity(), modelChallenge, R.layout.list_challenge);
        BaseHelper.setupRecyclerView(recyclerViewChallenge, adapterChallenge);

        return view;
    }

    private ArrayList<ModelChallenge> challangeData() {
        ArrayList<ModelChallenge> modelChallenge = new ArrayList<>();

        modelChallenge.add(new ModelChallenge(R.string.title_challenge1, R.string.keterangan_challenge1, true));
        modelChallenge.add(new ModelChallenge(R.string.title_challenge2, R.string.keterangan_challenge2, true));
        modelChallenge.add(new ModelChallenge(R.string.title_challenge3, R.string.keterangan_challenge3, true));
        modelChallenge.add(new ModelChallenge(R.string.title_challenge4, R.string.keterangan_challenge4, false));
        modelChallenge.add(new ModelChallenge(R.string.title_challenge5, R.string.keterangan_challenge5, false));

        return modelChallenge;
    }

}
