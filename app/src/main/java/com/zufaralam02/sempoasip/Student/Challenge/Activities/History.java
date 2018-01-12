package com.zufaralam02.sempoasip.Student.Challenge.Activities;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.Student.Challenge.Adapters.AdapterHistory;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelHistory;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class History extends BaseActivitySempoa {
    RecyclerView recyclerViewHistory;
    private final String judulHistory[] = {
            "Weekly Challange #1",
            "Weekly Challange #2",
            "Weekly Challange #3",
            "Weekly Challange #4",
            "Weekly Challange #5"

    };
    private final String yourScoreHistory[] = {
            "80",
            "76",
            "85",
            "76",
            "70"

    };
    private final String highScoreHistory[] = {
            "80",
            "80",
            "88",
            "80",
            "75"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerViewHistory = (RecyclerView) findViewById(R.id.recyclerViewHistory);
        ArrayList<ModelHistory> modelHistory = HistoryData();
        AdapterHistory adapterHistory = new AdapterHistory(this, modelHistory, R.layout.list_history);
        BaseHelper.setupRecyclerView(recyclerViewHistory, adapterHistory);

        setupNav("History");
    }

    private ArrayList<ModelHistory> HistoryData() {

        ArrayList<ModelHistory> modelHistory = new ArrayList<>();

        for (int i = 0; i < judulHistory.length; i++) {

            ModelHistory modelHistory1 = new ModelHistory();
            modelHistory1.setTvJudulHistory(judulHistory[i]);
            modelHistory1.setTvYourScroeHistory(yourScoreHistory[i]);
            modelHistory1.setTvHighScoreHistory(highScoreHistory[i]);
            modelHistory.add(modelHistory1);
        }

        return modelHistory;
    }
}
