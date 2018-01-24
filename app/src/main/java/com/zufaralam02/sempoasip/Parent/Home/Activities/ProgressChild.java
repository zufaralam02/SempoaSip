package com.zufaralam02.sempoasip.Parent.Home.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProgressChild extends BaseActivitySempoa {

    @BindView(R.id.cardFeedback1)
    CardView cardFeedback1;
    @BindView(R.id.cardFeedback2)
    CardView cardFeedback2;
    @BindView(R.id.cardFeedback3)
    CardView cardFeedback3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_child);
        ButterKnife.bind(this);

        setupNav("Progress");
    }

    @OnClick({R.id.cardFeedback1, R.id.cardFeedback2, R.id.cardFeedback3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cardFeedback1:
                startActivity(new Intent(this, FeedbackChild.class));
                break;
            case R.id.cardFeedback2:
                startActivity(new Intent(this, FeedbackChild.class));
                break;
            case R.id.cardFeedback3:
                startActivity(new Intent(this, FeedbackChild.class));
                break;
        }
    }
}
