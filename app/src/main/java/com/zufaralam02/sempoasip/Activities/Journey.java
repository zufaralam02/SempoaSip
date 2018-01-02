package com.zufaralam02.sempoasip.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Adapters.AdapterJourney;
import com.zufaralam02.sempoasip.Models.ModelJourney;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class Journey extends AppCompatActivity {
    RecyclerView recyclerViewJourney;
    private final String text[] = {
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text",
            "Text"
    };
    private final int image[] = {
            R.drawable.ic_journey_1_active,
            R.drawable.ic_journey_2_active,
            R.drawable.ic_journey_3_active,
            R.drawable.ic_journey_4_active,
            R.drawable.ic_journey_5_active,
            R.drawable.ic_journey_6_active,
            R.drawable.ic_journey_7_active,
            R.drawable.ic_journey_8_active,
            R.drawable.ic_journey_9_active,
            R.drawable.ic_journey_10_active,
            R.drawable.ic_journey_11_active

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journey);

        recyclerViewJourney = (RecyclerView) findViewById(R.id.recyclerViewJourney);
        ArrayList<ModelJourney> modelJourney = prepareData();
        AdapterJourney adapterJourney = new AdapterJourney(this, modelJourney, R.layout.list_journey);
        BaseHelper.setupRecyclerView(recyclerViewJourney, adapterJourney);

    }

    private ArrayList<ModelJourney> prepareData() {
        ArrayList<ModelJourney> modelJourney = new ArrayList<>();

        for (int i = 0; i < text.length; i++) {
            ModelJourney modelJourney1 = new ModelJourney();

            modelJourney1.setTextJourney(text[i]);
            modelJourney1.setImageJourney(image[i]);
            modelJourney.add(modelJourney1);

        }
        return modelJourney;
    }
}


