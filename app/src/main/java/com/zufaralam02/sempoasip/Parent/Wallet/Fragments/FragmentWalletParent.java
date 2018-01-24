package com.zufaralam02.sempoasip.Parent.Wallet.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Parent.Notification.Adapters.AdapterNotification;
import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotification;
import com.zufaralam02.sempoasip.Parent.Wallet.Activities.WalletHistory;
import com.zufaralam02.sempoasip.Parent.Wallet.Adapters.AdapterWallet;
import com.zufaralam02.sempoasip.Parent.Wallet.Models.ModelWallet;
import com.zufaralam02.sempoasip.R;
import com.zufaralam02.sempoasip.Student.Challenge.Activities.DetailChallenge;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWalletParent extends Fragment {
    RecyclerView recyclerWallet;

    public FragmentWalletParent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet_parent, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar_wallet);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        initialization(view);
        setupRecyclerView();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:
                customDialogWallet();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialization(View view) {
        recyclerWallet = view.findViewById(R.id.recyclerWallet);
    }

    private void setupRecyclerView() {
        ArrayList<ModelWallet> modelWallet = walletData();
        AdapterWallet adapterWallet = new AdapterWallet(getActivity(), modelWallet, R.layout.list_wallet);
        BaseHelper.setupRecyclerView(recyclerWallet, adapterWallet);
    }

    private ArrayList<ModelWallet> walletData() {
        ArrayList<ModelWallet> modelWallet = new ArrayList<>();

        modelWallet.add(new ModelWallet(R.string.wallet_name1, R.string.wallet_coin1, false));
        modelWallet.add(new ModelWallet(R.string.wallet_name2, R.string.wallet_coin2, false));
        modelWallet.add(new ModelWallet(R.string.wallet_name3, R.string.wallet_coin3, true));

        return modelWallet;
    }

    private void customDialogWallet() {
        final BottomSheetDialog builder = new BottomSheetDialog(getActivity());
        LayoutInflater inflater = getLayoutInflater();
        final View view = inflater.inflate(R.layout.custom_dialog_wallet, null);
        builder.setContentView(view);
        builder.setCancelable(false);
        view.findViewById(R.id.cardHistory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), WalletHistory.class));
                builder.cancel();
            }
        });
        view.findViewById(R.id.cardCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.cancel();
            }
        });
//        ((TextView) view.findViewById(R.id.tvTimerResult)).setText(tvCountDownTimer.getText().toString());
        builder.show();
    }

}
