package com.zufaralam02.sempoasip.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Activities.StageJourney;
import com.zufaralam02.sempoasip.R;

import org.w3c.dom.Text;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentStage extends Fragment {
    private int fragmentStage;
    private String[] stringStage;

    public static FragmentStage newInstance(int fragmentStage, String[] stringStage) {

        FragmentStage fragmentStage1 = new FragmentStage();
        Bundle bundle = new Bundle();
        bundle.putInt("fragmentStage", fragmentStage);
        bundle.putStringArray("stringStage", stringStage);
        fragmentStage1.setArguments(bundle);
        return fragmentStage1;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentStage = getArguments().getInt("fragmentStage", fragmentStage);
        stringStage = getArguments().getStringArray("stringStage");

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stage, container, false);

        TextView tvLeftStage = (TextView) view.findViewById(R.id.tvLeftStage);
        TextView tvCenterStage = (TextView) view.findViewById(R.id.tvCenterStage);
        TextView tvRightStage = (TextView) view.findViewById(R.id.tvRightStage);

//        tvLeftStage.setText("" + Arrays.toString(stringStage));

        for (int a = 0; a < stringStage.length; a++) {
            if (a < 5) {
                tvLeftStage.append("\n" + stringStage[a]);
                tvLeftStage.setVisibility(View.VISIBLE);
                tvCenterStage.setVisibility(View.GONE);
                tvRightStage.setVisibility(View.GONE);
            } else if (a < 10) {
                tvCenterStage.append("\n" + stringStage[a]);
                tvLeftStage.setVisibility(View.VISIBLE);
                tvCenterStage.setVisibility(View.VISIBLE);
                tvRightStage.setVisibility(View.GONE);
            } else {
                tvRightStage.append("\n" + stringStage[a]);
                tvLeftStage.setVisibility(View.VISIBLE);
                tvCenterStage.setVisibility(View.VISIBLE);
                tvRightStage.setVisibility(View.VISIBLE);
            }

        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
