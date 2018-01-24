package com.zufaralam02.sempoasip.Parent.Home.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class FeedbackChild extends BaseActivitySempoa {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_child);

        setupNav("Progress");
    }
}
