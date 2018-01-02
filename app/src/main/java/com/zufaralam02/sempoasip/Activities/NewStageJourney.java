package com.zufaralam02.sempoasip.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.zufaralam02.sempoasip.Adapters.AdapterNewStageJourney;
import com.zufaralam02.sempoasip.Fragments.FragmentNewStage;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewStageJourney extends AppCompatActivity {
    TextView tvCountDownTimerNew, tvTimeResult;
    Button btnOneNew, btnTwoNew, btnThreeNew, btnFourNew, btnFiveNew, btnSixNew, btnSevenNew, btnEightNew, btnNineNew, btnZeroNew;
    Button btnStageBackNew, btnStageNextNew, btnPlayAgain, btnBackToMap, btnDeleteNew;
    EditText edtStageJourneyNew;
    ViewPager viewPagerNew;
    CountDownTimer countDownTimer;

    //    List<String[]> listNewStage = new ArrayList<String[]>();
    List<Fragment> listFragment = new ArrayList<Fragment>();

//    FragmentNewStage fragmentNewStage;
//    NewStageJourney newStageJourney;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stage_journey);

        btnOneNew = (Button) findViewById(R.id.btnOneNew);
        btnTwoNew = (Button) findViewById(R.id.btnTwoNew);
        btnThreeNew = (Button) findViewById(R.id.btnThreeNew);
        btnFourNew = (Button) findViewById(R.id.btnFourNew);
        btnFiveNew = (Button) findViewById(R.id.btnFiveNew);
        btnSixNew = (Button) findViewById(R.id.btnSixNew);
        btnSevenNew = (Button) findViewById(R.id.btnSevenNew);
        btnEightNew = (Button) findViewById(R.id.btnEightNew);
        btnNineNew = (Button) findViewById(R.id.btnNineNew);
        btnZeroNew = (Button) findViewById(R.id.btnZeroNew);
        btnDeleteNew = (Button) findViewById(R.id.btnDeleteNew);
        btnStageBackNew = (Button) findViewById(R.id.btnStageBackNew);
        btnStageNextNew = (Button) findViewById(R.id.btnStageNextNew);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        btnBackToMap = (Button) findViewById(R.id.btnBackToMap);
        tvTimeResult = (TextView) findViewById(R.id.tvTimerResult);
        tvCountDownTimerNew = (TextView) findViewById(R.id.tvCountDownTimerNew);
        edtStageJourneyNew = (EditText) findViewById(R.id.edtStegeJourneyNew);
        viewPagerNew = (ViewPager) findViewById(R.id.viewPagerNew);
//        viewPagerNew.setOffscreenPageLimit(0);
//        nextPage();

        AdapterNewStageJourney fragmentNewPagerAdapter = new AdapterNewStageJourney(getSupportFragmentManager());
//        listNewStage.add(new String[]{"6", "7", "4", "2", "1"});
//        listNewStage.add(new String[]{"2", "4", "6", "8", "10"});
//        listNewStage.add(new String[]{"1", "3", "5", "7", "9"});

        FragmentNewStage fragmentNewStage = FragmentNewStage.newInstance(0, new String[]{"6", "7", "4", "2", "1"});
        fragmentNewStage.setParent(this);
        listFragment.add(fragmentNewStage);

        FragmentNewStage fragmentNewStage2 = FragmentNewStage.newInstance(1, new String[]{"1", "3", "5", "7", "9"});
        fragmentNewStage2.setParent(this);
        listFragment.add(fragmentNewStage2);

        fragmentNewPagerAdapter.setListFragment(listFragment);
        viewPagerNew.setAdapter(fragmentNewPagerAdapter);
//        fragmentNewStage.startTimer();

        fragmentNewPagerAdapter.setNewStageJourney(this);
        fragmentNewPagerAdapter.setListFragment(listFragment);

        countDownTimer = new CountDownTimer(601000, 1000) {

//            public void onTick(long millisUntilFinished) {
//                tvCountDownTimer.setText("" + millisUntilFinished / 1000);
//            }

            @SuppressLint({"DefaultLocale", "SetTextI18n"})
            @Override
            public void onTick(long millisUntilFinished) {
                tvCountDownTimerNew.setText("" + String.format("%d:%d",
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
                tvCountDownTimerNew.setText("Done");
            }
        }.start();


        btnStageBackNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPagerNew.setCurrentItem(viewPagerNew.getCurrentItem() - 1);

            }
        });

        btnStageNextNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewPagerNew.setCurrentItem(viewPagerNew.getCurrentItem() + 1);
                nextPage(viewPagerNew.getCurrentItem() + 1);
            }
        });


        viewPagerNew.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                if (viewPagerNew.getCurrentItem() == 0) {
                    btnStageBackNew.setVisibility(View.GONE);
                    btnStageNextNew.setVisibility(View.VISIBLE);
                } else if (viewPagerNew.getCurrentItem() == listFragment.size() - 1) {
                    btnStageBackNew.setVisibility(View.VISIBLE);
                    btnStageNextNew.setVisibility(View.VISIBLE);
//                    btnStageNextNew.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            customDialog();
//                            countDownTimer.cancel();
//
//                        }
//                    });
                } else {
                    btnStageBackNew.setVisibility(View.VISIBLE);
                    btnStageNextNew.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        btnDeleteNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtStageJourneyNew.length() == 0) {
//                    Toast.makeText(StageJourney.this, "empty", Toast.LENGTH_SHORT).show();
                } else {
                    String delete = edtStageJourneyNew.getText().toString();
                    delete = delete.substring(0, delete.length() - 1);
                    edtStageJourneyNew.setText(delete);
                }
            }
        });

//        btnOneNew.setOnClickListener(this);
//        btnTwoNew.setOnClickListener(this);

        btnOneNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "1");
            }
        });
        btnTwoNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "2");
            }
        });
        btnThreeNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "3");
            }
        });
        btnFourNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "4");
            }
        });
        btnFiveNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "5");
            }
        });
        btnSixNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "6");
            }
        });
        btnSevenNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "7");
            }
        });
        btnEightNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "8");
            }
        });
        btnNineNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "9");
            }
        });
        btnZeroNew.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                edtStageJourneyNew.setText(edtStageJourneyNew.getText() + "0");
            }
        });
    }

    public void nextPage(int index) {
        viewPagerNew.setCurrentItem(viewPagerNew.getCurrentItem() + 1);

        FragmentNewStage fragmentNewStage = (FragmentNewStage) listFragment.get(viewPagerNew.getCurrentItem());
//        fragmentNewStage.startTimer();


        if (index == listFragment.size()) {
            customDialog();
            fragmentNewStage.stopTimer();
            countDownTimer.cancel();
        } else {
            fragmentNewStage.startTimer();
        }

//        fragmentNewStage.setParent(newStageJourney);
//        fragmentNewStage.startTimer();

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
                startActivity(new Intent(getApplicationContext(), NewStageJourney.class));
                finish();
            }
        });
        view.findViewById(R.id.btnBackToMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        try {
            ((TextView) view.findViewById(R.id.tvTimerResult)).setText(tvCountDownTimerNew.getText().toString());
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnOneNew:
//
//                break;
//        }
//    }


}
