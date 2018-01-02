package com.zufaralam02.sempoasip.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Adapters.AdapterRank;
import com.zufaralam02.sempoasip.Models.ModelJourney;
import com.zufaralam02.sempoasip.Models.ModelRank;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class Rank extends AppCompatActivity {
    RecyclerView recyclerViewRank;
    private final String rankNumber[] = {
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10"
    };
    private final String rankName[] = {
            "Joshua - 04:05",
            "Joshua - 05:28",
            "Joshua - 04:21",
            "You - 05:31",
            "Albert - 03:31",
            "Yasmin - 04:21",
            "Gio - 04:31",
            "Josh - 06:51",
            "Joshua - 05:31",
            "Joshua - 05:31"
    };
    private final String rankTime[] = {
            "40 - 08:23",
            "23 - 07:23",
            "120 - 08:23",
            "Get Reward",
            "12 - 08:23",
            "23 - 08:23",
            "34 - 08:23",
            "67 - 08:23",
            "12 - 08:23",
            "99 - 08:23"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        recyclerViewRank = (RecyclerView) findViewById(R.id.recyclerViewRank);
        ArrayList<ModelRank> modelRank = rankData();
        AdapterRank adapterRank = new AdapterRank(this, modelRank, R.layout.list_rank);
        BaseHelper.setupRecyclerView(recyclerViewRank, adapterRank);
    }

    private ArrayList<ModelRank> rankData() {

        ArrayList<ModelRank> modelRank = new ArrayList<>();

        for (int i = 0; i < rankNumber.length; i++) {
            ModelRank modelRank1 = new ModelRank();

            modelRank1.setTvRankNumber(rankNumber[i]);
            modelRank1.setTvRankName(rankName[i]);
            modelRank1.setTvRankTime(rankTime[i]);
            modelRank.add(modelRank1);

        }
        return modelRank;
    }
}
