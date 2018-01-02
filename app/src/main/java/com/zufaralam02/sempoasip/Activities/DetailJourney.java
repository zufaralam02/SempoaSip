package com.zufaralam02.sempoasip.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Adapters.AdapterDetailJourney;
import com.zufaralam02.sempoasip.Models.ModelDetailJourney;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class DetailJourney extends AppCompatActivity {
    RelativeLayout rlDetailJourney;
    int positionJourney;
    RecyclerView recyclerViewDetailJourney;

    private final String textDetailJourney[] = {
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text"

    };
    private final int imageDetailJourney[] = {
            R.drawable.item_1_1_1,
            R.drawable.item_1_1_2,
            R.drawable.item_1_2_1,
            R.drawable.item_1_2_2,
            R.drawable.item_1_3_1,
            R.drawable.item_1_3_2,
            R.drawable.item_1_4_1,
            R.drawable.item_1_4_2

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_journey);

        rlDetailJourney = (RelativeLayout) findViewById(R.id.rldetailJourneyLayout);
        recyclerViewDetailJourney = (RecyclerView) findViewById(R.id.recyclerViewDetailJourney);
        ArrayList<ModelDetailJourney> modelDetailJourney = dataDetailJouney();
        AdapterDetailJourney adapterDetailJourney = new AdapterDetailJourney(this, modelDetailJourney,
                R.layout.list_detail_journey) {
            @Override
            public void itemSelected(int position) {
                if (position == 0) {
                    startActivity(new Intent(DetailJourney.this, StageJourney.class));
                } else if (position == getItemCount() - 1) {
                    startActivity(new Intent(DetailJourney.this, NewStageJourney.class));
                } else if (position % 2 == 1) {
                    startActivity(new Intent(DetailJourney.this, NewStageJourney.class));
                } else if (position % 2 == 0) {
                    startActivity(new Intent(DetailJourney.this, StageJourney.class));
                }
//                Intent intent = new Intent(DetailJourney.this, StageJourney.class);
//                startActivity(intent);
            }
        };

        positionJourney = getIntent().getIntExtra("journeyPosition", 0);
        adapterDetailJourney.setPositionJourney(positionJourney);
        BaseHelper.setupRecyclerView(recyclerViewDetailJourney, adapterDetailJourney);

        adapterDetailJourney.setRlDetailJourneyLayout(rlDetailJourney);

        TextView tvRank = (TextView) findViewById(R.id.tvRank);
        tvRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Rank.class);
                startActivity(intent);
            }
        });

    }

    public ArrayList<ModelDetailJourney> dataDetailJouney() {
        ArrayList<ModelDetailJourney> modelDetailJourney = new ArrayList<>();

        modelDetailJourney.add(new ModelDetailJourney("1", true));
        modelDetailJourney.add(new ModelDetailJourney("1 a", true));
        modelDetailJourney.add(new ModelDetailJourney("2", true));
        modelDetailJourney.add(new ModelDetailJourney("2 a", true));
        modelDetailJourney.add(new ModelDetailJourney("3", false));
        modelDetailJourney.add(new ModelDetailJourney("3 a", false));
        modelDetailJourney.add(new ModelDetailJourney("4", false));
        modelDetailJourney.add(new ModelDetailJourney("4 a", false));
        modelDetailJourney.add(new ModelDetailJourney("5", false));
        modelDetailJourney.add(new ModelDetailJourney("5 a", false));

//        modelDetailJourney.add(new ModelDetailJourney("Text 1", R.drawable.item_1_1, R.drawable.item_1_2));
//        modelDetailJourney.add(new ModelDetailJourney("Text 2", R.drawable.item_2_1, R.drawable.item_2_2));
//        modelDetailJourney.add(new ModelDetailJourney("Text 3", R.drawable.item_3_1, R.drawable.item_3_2));
//        modelDetailJourney.add(new ModelDetailJourney("Text 4", R.drawable.item_4_1, R.drawable.item_4_2));

//        for (int a = 0; a < textDetailJourney.length; a++) {
//            ModelDetailJourney modelDetailJourney1 = new ModelDetailJourney();
//            modelDetailJourney1.setTextDetailJourney1(textDetailJourney[a]);
//            modelDetailJourney1.setTextDetailJourney2(textDetailJourney[a]);
//            modelDetailJourney.add(modelDetailJourney1);
//        }

        return modelDetailJourney;
    }

}
