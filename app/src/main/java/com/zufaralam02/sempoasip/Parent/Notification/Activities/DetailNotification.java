package com.zufaralam02.sempoasip.Parent.Notification.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

public class DetailNotification extends BaseActivitySempoa implements View.OnClickListener {
    ImageView ivDetailNotif;
    TextView tvTitleDetailNotif, tvTimeDetailNotif, tvDetailDetailNotif;
    Button btnDetailNotif;
    String title, time, detail, image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        setupNav("Notification");
        initialization();
        setupWidget();

//        title = getIntent().getStringExtra("titleNotif");
//        time = getIntent().getStringExtra("timeNotif");
//        detail = getIntent().getStringExtra("detailNotif");
//        tvTitleDetailNotif.setText(title);
//        tvTimeDetailNotif.setText(time);
//        tvDetailDetailNotif.setText(detail);

//        image = getIntent().getIntExtra("imageNotif", 0);
//        Picasso.with(getApplicationContext()).load(image)
//                .placeholder(android.R.drawable.ic_menu_gallery)
//                .error(android.R.drawable.ic_menu_report_image)
//                .into(ivDetailNotif);

        tvTitleDetailNotif.setText(getIntent().getIntExtra("titleNotif", 0));
        tvTimeDetailNotif.setText(getIntent().getIntExtra("timeNotif", 0));
        tvDetailDetailNotif.setText(getIntent().getIntExtra("detailNotif", 0));
        ivDetailNotif.setImageResource(getIntent().getIntExtra("imageNotif", 0));

//        Bundle bundle = new Bundle();
//        tvTitleDetailNotif.setText(String.valueOf(bundle.getString("titleNotif")));
//        tvTimeDetailNotif.setText(String.valueOf(bundle.getString("timeNotif")));
//        tvDetailDetailNotif.setText(String.valueOf(bundle.getString("detailNotif")));
//        ivDetailNotif.setImageResource(bundle.getInt("imageNotif"));

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private void initialization() {
        ivDetailNotif = findViewById(R.id.ivDetailNotif);
        tvTitleDetailNotif = findViewById(R.id.tvTitleDetailNotif);
        tvTimeDetailNotif = findViewById(R.id.tvTimeDetailNotif);
        tvDetailDetailNotif = findViewById(R.id.tvDetailDetailNotif);
        btnDetailNotif = findViewById(R.id.btnDetailNotif);
    }

    private void setupWidget() {
        btnDetailNotif.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDetailNotif:

                break;
        }

    }

//    public void getDetail(String title, String time, String detail) {
//        this.title = title;
//        this.time = time;
//        this.detail = detail;
//    }

}
