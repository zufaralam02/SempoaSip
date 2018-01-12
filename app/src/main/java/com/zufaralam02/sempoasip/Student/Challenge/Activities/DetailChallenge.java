package com.zufaralam02.sempoasip.Student.Challenge.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;

public class DetailChallenge extends BaseActivitySempoa implements View.OnClickListener {
    HorizontalScrollView scrollViewChallenge;
    Button btnOneChallenge, btnTwoChallenge, btnThreeChallenge, btnFourChallenge, btnFiveChallenge, btnSixChallenge,
            btnSevenChallenge, btnEightChallenge, btnNineChallenge, btnZeroChallenge, btnNextChallenge, btnDeleteChallenge;
    LinearLayout linearLayoutChallenge;

    ArrayList<String[]> listItem = new ArrayList<String[]>();
    EditText editTextItem;
    TextView textViewItem;

    View activeView, prevView, activeEdt;
    ArrayList<View> arrayListView = new ArrayList<View>();
    int activePost = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_challenge);

        initialization();
        setupScrollView();
        setupButton();

    }

    private void initialization() {
        scrollViewChallenge = (HorizontalScrollView) findViewById(R.id.scrollViewChallenge);
        linearLayoutChallenge = (LinearLayout) findViewById(R.id.linearLayoutChallenge);
        btnOneChallenge = (Button) findViewById(R.id.btnOneChallenge);
        btnTwoChallenge = (Button) findViewById(R.id.btnTwoChallenge);
        btnThreeChallenge = (Button) findViewById(R.id.btnThreeChallenge);
        btnFourChallenge = (Button) findViewById(R.id.btnFourChallenge);
        btnFiveChallenge = (Button) findViewById(R.id.btnFiveChallenge);
        btnSixChallenge = (Button) findViewById(R.id.btnSixChallenge);
        btnSevenChallenge = (Button) findViewById(R.id.btnSevenChallenge);
        btnEightChallenge = (Button) findViewById(R.id.btnEightChallenge);
        btnNineChallenge = (Button) findViewById(R.id.btnNineChallenge);
        btnZeroChallenge = (Button) findViewById(R.id.btnZeroChallenge);
        btnNextChallenge = (Button) findViewById(R.id.btnNextChallenge);
        btnDeleteChallenge = (Button) findViewById(R.id.btnDeleteChallenge);
    }

    @SuppressLint("SetTextI18n")
    private void setupScrollView() {

        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});
        listItem.add(new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"});

//        String[] item = {"0\n1\n2\n3\n4\n5\n6\n7\n8\n9"};
//        String[] item = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        for (int i = 0; i < listItem.size(); i++) {

            View view = getLayoutInflater().inflate(R.layout.list_detail_challenge, linearLayoutChallenge, false);

            editTextItem = (EditText) view.findViewById(R.id.editTextItem);
            editTextItem.setInputType(InputType.TYPE_NULL);
            textViewItem = (TextView) view.findViewById(R.id.textViewItem);
            linearLayoutChallenge.addView(view);

            String[] strings = listItem.get(i);
            for (int a = 0; a < strings.length; a++) {
                textViewItem.append("\n" + strings[a]);
                textViewItem.setText(textViewItem.getText().toString().trim());
                textViewItem.setTextColor(getApplicationContext().getResources().getColor(R.color.
                        text_color_detail_challenge_default));
//                String text = strings[a];
//                textViewItem.append(text + "\n");
            }
            arrayListView.add(view);
            if (activePost == 0) {
                prevView = arrayListView.get(activePost);
                TextView textView = (TextView) prevView.findViewById(R.id.textViewItem);
                EditText editText = (EditText) prevView.findViewById(R.id.editTextItem);
                editTextItem = editText;
                textView.setTextColor(getApplicationContext().getResources().getColor(R.color.text_color_detail_challenge));
            }
        }
    }

    public void nextChallenge() {
        if (activePost >= arrayListView.size() - 1) {
            customDialogNew();
            return;
        }

        prevView = arrayListView.get(activePost);
        TextView textView1 = (TextView) prevView.findViewById(R.id.textViewItem);
        textView1.setTextColor(getApplicationContext().getResources().getColor(R.color.text_color_detail_challenge_inactive));

        activePost = activePost + 1;
        activeView = arrayListView.get(activePost);
        EditText editText = (EditText) activeView.findViewById(R.id.editTextItem);
        editTextItem = editText;

        TextView textView = (TextView) activeView.findViewById(R.id.textViewItem);
        textView.setTextColor(getApplicationContext().getResources().getColor(R.color.text_color_detail_challenge));

        if (activePost > 3)
            scrollViewChallenge.smoothScrollTo(activeView.getMeasuredWidth() * (activePost - 3), 0);

    }

    private void setupButton() {
        btnOneChallenge.setOnClickListener(this);
        btnTwoChallenge.setOnClickListener(this);
        btnThreeChallenge.setOnClickListener(this);
        btnFourChallenge.setOnClickListener(this);
        btnFiveChallenge.setOnClickListener(this);
        btnSixChallenge.setOnClickListener(this);
        btnSevenChallenge.setOnClickListener(this);
        btnEightChallenge.setOnClickListener(this);
        btnNineChallenge.setOnClickListener(this);
        btnZeroChallenge.setOnClickListener(this);
        btnNextChallenge.setOnClickListener(this);
        btnDeleteChallenge.setOnClickListener(this);

    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnOneChallenge:
                editTextItem.setText(editTextItem.getText() + "1");
                break;
            case R.id.btnTwoChallenge:
                editTextItem.setText(editTextItem.getText() + "2");
                break;
            case R.id.btnThreeChallenge:
                editTextItem.setText(editTextItem.getText() + "3");
                break;
            case R.id.btnFourChallenge:
                editTextItem.setText(editTextItem.getText() + "4");
                break;
            case R.id.btnFiveChallenge:
                editTextItem.setText(editTextItem.getText() + "5");
                break;
            case R.id.btnSixChallenge:
                editTextItem.setText(editTextItem.getText() + "6");
                break;
            case R.id.btnSevenChallenge:
                editTextItem.setText(editTextItem.getText() + "7");
                break;
            case R.id.btnEightChallenge:
                editTextItem.setText(editTextItem.getText() + "8");
                break;
            case R.id.btnNineChallenge:
                editTextItem.setText(editTextItem.getText() + "9");
                break;
            case R.id.btnZeroChallenge:
                editTextItem.setText(editTextItem.getText() + "0");
                break;
            case R.id.btnNextChallenge:
                nextChallenge();
                break;
            case R.id.btnDeleteChallenge:
                if (!(editTextItem.length() == 0)) {
                    String delete = editTextItem.getText().toString();
                    delete = delete.substring(0, delete.length() - 1);
                    editTextItem.setText(delete);
                }
                break;
        }
    }

    private void customDialogNew() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.custom_dialog_new, null);
        builder.setView(view);
        builder.setCancelable(false);
//        builder.setCancelable(true);
//        builder.setTitle();
        view.findViewById(R.id.btnPlayAgain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DetailChallenge.class));
                finish();
            }
        });
        view.findViewById(R.id.btnBackToMap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        ((TextView) view.findViewById(R.id.tvTimerResult)).setText(tvCountDownTimer.getText().toString());
        builder.show();
    }

}
