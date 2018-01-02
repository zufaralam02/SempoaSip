package com.zufaralam02.sempoasip.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zufaralam02.sempoasip.Adapters.AdapterStageJourney;
import com.zufaralam02.sempoasip.Fragments.FragmentStage;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class StageJourney extends AppCompatActivity {
    //    FragmentPagerAdapter fragmentPagerAdapter;
    TextView tvCountDownTimer, tvTimeResult;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero;
    Button btnStageBack, btnStageNext, btnPlayAgain, btnBackToMap, btnDelete;
    EditText edtStageJourney;

    List<String[]> listStage = new ArrayList<String[]>();

//    String[] stageOne = {
//            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
//    };
//    String[] stageTwo = {
//            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
//    };
//    String[] stageThree = {
//            "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage_journey);

        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnBackToMap = (Button) findViewById(R.id.btnBackToMap);
        tvTimeResult = (TextView) findViewById(R.id.tvTimerResult);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        AdapterStageJourney adapterStageJourney = new AdapterStageJourney(getSupportFragmentManager());
        listStage.add(new String[]{"6", "7", "4", "2", "1"});
        listStage.add(new String[]{"2", "4", "6", "8", "10"});
        listStage.add(new String[]{"1", "3", "5", "7", "9"});
        adapterStageJourney.setListStage(listStage);
        viewPager.setAdapter(adapterStageJourney);

//        listStage.add(stageOne);
//        listStage.add(stageTwo);
//        listStage.add(stageThree);

        tvCountDownTimer = (TextView) findViewById(R.id.tvCountDownTimer);
        final CountDownTimer countDownTimer = new CountDownTimer(601000, 1000) {

//            public void onTick(long millisUntilFinished) {
//                tvCountDownTimer.setText("" + millisUntilFinished / 1000);
//            }

            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long millisUntilFinished) {
                tvCountDownTimer.setText("" + String.format("%d:%d",
//                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
//                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
//                                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
//                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
//                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds((TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)))));
            }

            public void onFinish() {
                tvCountDownTimer.setText("Done");

            }

        }.start();

        btnStageNext = (Button) findViewById(R.id.btnStageNext);
        btnStageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
            }
        });

        btnStageBack = (Button) findViewById(R.id.btnStageBack);
        btnStageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (viewPager.getCurrentItem() == 0) {
                    btnStageBack.setVisibility(View.GONE);
                    btnStageNext.setVisibility(View.VISIBLE);
                } else if (viewPager.getCurrentItem() == listStage.size() - 1) {
                    btnStageBack.setVisibility(View.VISIBLE);
                    btnStageNext.setVisibility(View.VISIBLE);
                    btnStageNext.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            customDialog();
                            countDownTimer.cancel();

                        }
                    });
                } else {
                    btnStageBack.setVisibility(View.VISIBLE);
                    btnStageNext.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        btnOne = (Button) findViewById(R.id.btnOne);
        btnTwo = (Button) findViewById(R.id.btnTwo);
        btnThree = (Button) findViewById(R.id.btnThree);
        btnFour = (Button) findViewById(R.id.btnFour);
        btnFive = (Button) findViewById(R.id.btnFive);
        btnSix = (Button) findViewById(R.id.btnSix);
        btnSeven = (Button) findViewById(R.id.btnSeven);
        btnEight = (Button) findViewById(R.id.btnEight);
        btnNine = (Button) findViewById(R.id.btnNine);
        btnZero = (Button) findViewById(R.id.btnZero);
        edtStageJourney = (EditText) findViewById(R.id.edtStegeJourney);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStageJourney.length() == 0) {
//                    Toast.makeText(StageJourney.this, "empty", Toast.LENGTH_SHORT).show();
                } else {
                    String delete = edtStageJourney.getText().toString();
                    delete = delete.substring(0, delete.length() - 1);
                    edtStageJourney.setText(delete);
                }
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "1");
            }
        });
        btnTwo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "2");
            }
        });
        btnThree.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "3");
            }
        });
        btnFour.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "4");
            }
        });
        btnFive.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "5");
            }
        });
        btnSix.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "6");
            }
        });
        btnSeven.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "7");
            }
        });
        btnEight.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "8");
            }
        });
        btnNine.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "9");
            }
        });
        btnZero.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourney.setText(edtStageJourney.getText() + "0");
            }
        });

    }

    private void customDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.custom_dialog, null);
        builder.setView(view);
//        builder.setCancelable(true);
//        builder.setTitle();
        view.findViewById(R.id.btnPlayAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), StageJourney.class));
                finish();
            }
        });
        view.findViewById(R.id.btnBackToMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ((TextView) view.findViewById(R.id.tvTimerResult)).setText(tvCountDownTimer.getText().toString());
        builder.show();
    }

}
