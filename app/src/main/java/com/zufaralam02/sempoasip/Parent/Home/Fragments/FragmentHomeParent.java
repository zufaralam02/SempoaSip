package com.zufaralam02.sempoasip.Parent.Home.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Parent.Home.Activities.ProgressChild;
import com.zufaralam02.sempoasip.Parent.Home.Adapters.AdapterChildHome;
import com.zufaralam02.sempoasip.Parent.LoginRegister.Activities.AddChild;
import com.zufaralam02.sempoasip.Parent.Wallet.Fragments.FragmentWalletParent;
import com.zufaralam02.sempoasip.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//import com.zufaralam02.sempoasip.Parent.Home.Adapters.AdapterChildHome;
//import com.zufaralam02.sempoasip.Parent.Home.Models.ModelChildHome;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHomeParent extends Fragment {
    List<String[]> listChild = new ArrayList<String[]>();
    String[] childOne = {
            "Middlestone",
            "111222",
            "Tangerang"
    };
    String[] childTwo = {
            "Mark",
            "333444",
            "jakarta"
    };
    String[] childThree = {
            "Martin",
            "555666",
            "Depok"
    };
    @BindView(R.id.btnAddChildHome)
    Button btnAddChildHome;
    @BindView(R.id.viewPagerChildHome)
    ViewPager viewPagerChildHome;
    @BindView(R.id.cardProgressHomeParent)
    CardView cardProgressHomeParent;
    @BindView(R.id.cardWalletHomeParent)
    CardView cardWalletHomeParent;
    @BindView(R.id.cardRankHomeParent)
    CardView cardRankHomeParent;
    @BindView(R.id.frameHomeParent)
    FrameLayout frameHomeParent;
    Unbinder unbinder;
    @BindView(R.id.tvChildWallet)
    TextView tvChildWallet;
    @BindView(R.id.tvChildRank)
    TextView tvChildRank;
    AdapterChildHome adapterChildHome;

    public FragmentHomeParent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_parent, container, false);
        unbinder = ButterKnife.bind(this, view);

//        ArrayList<ModelChildHome> modelChildHome = childHomeData();
//        AdapterChildHome adapterChildHome = new AdapterChildHome(getActivity().getSupportFragmentManager());
//        viewPagerChildHome.setAdapter(adapterChildHome);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setReenterTransition(true);

        if (adapterChildHome == null) {
            adapterChildHome = new AdapterChildHome(getActivity().getSupportFragmentManager());
            listChild.add(childOne);
            listChild.add(childTwo);
            listChild.add(childThree);
            adapterChildHome.setListChild(listChild);
            adapterChildHome.setTvChildRank(tvChildRank);
            adapterChildHome.setTvChildWallet(tvChildRank);

            viewPagerChildHome.setClipToPadding(false);
            viewPagerChildHome.setPadding(100, 0, 100, 0);
            viewPagerChildHome.setPageMargin(20);
            viewPagerChildHome.setSaveFromParentEnabled(false);
        } else {

        }
        viewPagerChildHome.setAdapter(adapterChildHome);

    }

    //    private ArrayList<ModelChildHome> childHomeData() {
//        ArrayList<ModelChildHome> modelChildHome = new ArrayList<>();
//
//        modelChildHome.add(new ModelChildHome("Middletone Henry", "123123", "Tangerang"));
//        modelChildHome.add(new ModelChildHome("Mark Henry", "234234", "Jakarta"));
//        modelChildHome.add(new ModelChildHome("Martin Henry", "345345", "Depok"));
//
//        return modelChildHome;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    public BottomNavigationView getNavigation() {
        return navigation;
    }

    public void setNavigation(BottomNavigationView navigation) {
        this.navigation = navigation;
    }

    BottomNavigationView navigation;

    @OnClick({R.id.btnAddChildHome, R.id.cardProgressHomeParent, R.id.cardWalletHomeParent, R.id.cardRankHomeParent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddChildHome:
                startActivity(new Intent(getActivity(), AddChild.class));
                break;
            case R.id.cardProgressHomeParent:
                startActivity(new Intent(getActivity(), ProgressChild.class));
                break;
            case R.id.cardWalletHomeParent:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameParent, new FragmentWalletParent()).commit();
//                navigation.setSelectedItemId(R.id.navigation_wallet_parent);
                break;
            case R.id.cardRankHomeParent:
                break;
        }
    }
}
