package com.zufaralam02.sempoasip.Parent.Home.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zufaralam02.sempoasip.Parent.Home.Activities.ChildSetting;
import com.zufaralam02.sempoasip.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentChildHome extends Fragment {
    @BindView(R.id.tvNameChildHome)
    TextView tvNameChildHome;
    @BindView(R.id.tvNumberChildHome)
    TextView tvNumberChildHome;
    @BindView(R.id.tvPlaceChildHome)
    TextView tvPlaceChildHome;
    @BindView(R.id.ivLocationChildHome)
    ImageView ivLocationChildHome;
    @BindView(R.id.ivSettingChildHome)
    ImageView ivSettingChildHome;
    Unbinder unbinder;
    private int position;
    private String[] childName, childNumber, childLocation;

    public static FragmentChildHome newInstance(int position, String[] childName) {

        FragmentChildHome fragmentChildHome = new FragmentChildHome();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putStringArray("childName", childName);
//        bundle.putString("childNumber", childNumber);
//        bundle.putString("childLocation", childLocation);
        fragmentChildHome.setArguments(bundle);
        return fragmentChildHome;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position = getArguments().getInt("position", position);
        childName = getArguments().getStringArray("childName");
//        childNumber = getArguments().getString("childNumber");
//        childLocation = getArguments().getString("childLocation");

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_home, container, false);
        unbinder = ButterKnife.bind(this, view);

        for (int a = 0; a < childName.length; a++) {
            tvNameChildHome.append("\n" + childName[a].trim());
//        tvNumberChildHome.setText(childNumber.trim());
//        tvPlaceChildHome.setText(childLocation.trim());
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.ivLocationChildHome, R.id.ivSettingChildHome})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivLocationChildHome:
                break;
            case R.id.ivSettingChildHome:
                startActivity(new Intent(getActivity(), ChildSetting.class));
                break;
        }
    }
}
