package com.zufaralam02.sempoasip.Student.Journey.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Student.Journey.Activities.NewStageJourney;
import com.zufaralam02.sempoasip.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNewStage extends Fragment {
    private int index;
    private String[] stringNewStage;
    private TextView tvQuestionNumber;
    private int counter;
    CountDownTimer countDownTimer;

    public NewStageJourney getParent() {
        return parent;
    }

    public void setParent(NewStageJourney newStageJourney) {
        this.parent = newStageJourney;
    }

    private NewStageJourney parent;

//    public List<Fragment> getListFragment() {
//        return listFragment;
//    }
//
//    public void setListFragment(List<Fragment> listFragment) {
//        this.listFragment = listFragment;
//    }
//
//    private List<Fragment> listFragment;

//    public static FragmentNewStage newInstance(int index, String[] stringNewStage) {
//        // Required empty public constructor
//        FragmentNewStage fragmentNewStage1 = new FragmentNewStage();
//        Bundle bundle = new Bundle();
//        bundle.putInt("index", index);
//        bundle.putStringArray("stringNewStage", stringNewStage);
//        fragmentNewStage1.setArguments(bundle);
//        return fragmentNewStage1;
//
//    }

    public static FragmentNewStage newInstance(int fragmentNewStage, String[] stringNewStage) {
        FragmentNewStage fragmentNewStage1 = new FragmentNewStage();
        Bundle bundle = new Bundle();
        bundle.putInt("index", fragmentNewStage);
        bundle.putStringArray("stringNewStage", stringNewStage);
        fragmentNewStage1.setArguments(bundle);
        return fragmentNewStage1;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        index = getArguments().getInt("index", index);
        stringNewStage = getArguments().getStringArray("stringNewStage");

    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_stage, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvQuestionNumber = (TextView) view.findViewById(R.id.tvQuestionNumber);

        if (index == 0)
            startTimer();

        //        for (int a = 0; a < stringNewStage.length; a++) {
//            tvQuestionNumber.setText("" + stringNewStage[a]);
//        }

    }

    private void timer(final int counter) {
        countDownTimer = new CountDownTimer(2000, 1000) {
            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                if (counter < stringNewStage.length) {
                    tvQuestionNumber.setText("" + stringNewStage[counter]);
                    timer(counter + 1);
                } else {
                    tvQuestionNumber.setText("");
                    parent.nextPage(index + 1);

                }

            }
        }.start();
    }

    public void startTimer() {
        try {
            tvQuestionNumber.setText(stringNewStage[counter]);
            timer(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void stopTimer() {
        countDownTimer.cancel();
    }

}
