package com.zufaralam02.sempoasip.Parent.Notification.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iapps.libs.helpers.BaseHelper;
import com.zufaralam02.sempoasip.Parent.Notification.Adapters.AdapterNotification;
import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotification;
import com.zufaralam02.sempoasip.R;
import com.zufaralam02.sempoasip.Student.Challenge.Adapters.AdapterHistory;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelChallenge;
import com.zufaralam02.sempoasip.Student.Challenge.Models.ModelHistory;
import com.zufaralam02.sempoasip.Student.Journey.Adapters.AdapterRank;
import com.zufaralam02.sempoasip.Student.Journey.Models.ModelRank;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNotificationParent extends Fragment {
    RecyclerView recyclerNotification;
    private String titleNotif[] = {
            "Title Notification Here",
            "Title Notification Here",
            "Title Notification Here",
            "Title Notification Here",
            "Title Notification Here"

    };
    private String detailNotif[] = {
            "Detail Notification Here",
            "Detail Notification Here",
            "Detail Notification Here",
            "Detail Notification Here",
            "Detail Notification Here"

    };
    private String timeNotif[] = {
            "24 Sept 2017, 14.00",
            "24 Sept 2017, 14.00",
            "24 Sept 2017, 14.00",
            "24 Sept 2017, 14.00",
            "24 Sept 2017, 14.00"

    };
    private int imageNotif[] = {
            R.drawable.ic_notif,
            R.drawable.ic_call,
            R.drawable.ic_wallet,
            R.drawable.ic_announcement,
            R.drawable.ic_comment

    };

    public FragmentNotificationParent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_parent, container, false);

        recyclerNotification = view.findViewById(R.id.recyclerNotification);
        ArrayList<ModelNotification> modelNotification = notificationData();
        AdapterNotification adapterNotification = new AdapterNotification(getActivity(), modelNotification, R.layout.list_notification);
        BaseHelper.setupRecyclerView(recyclerNotification, adapterNotification);
        adapterNotification.setModelNotification(modelNotification);

        return view;
    }

    private ArrayList<ModelNotification> notificationData() {

        ArrayList<ModelNotification> modelNotification = new ArrayList<>();

        modelNotification.add(new ModelNotification(R.string.title_notif1, R.string.detail_notif1, R.string.time_notif1,
                R.drawable.ic_notif, true));
        modelNotification.add(new ModelNotification(R.string.title_notif2, R.string.detail_notif2, R.string.time_notif2,
                R.drawable.ic_comment, true));
        modelNotification.add(new ModelNotification(R.string.title_notif3, R.string.detail_notif3, R.string.time_notif3,
                R.drawable.ic_wallet, false));
        modelNotification.add(new ModelNotification(R.string.title_notif4, R.string.detail_notif4, R.string.time_notif4,
                R.drawable.ic_announcement, false));
        modelNotification.add(new ModelNotification(R.string.title_notif5, R.string.detail_notif5, R.string.time_notif5,
                R.drawable.ic_call, false));

//        for (int i = 0; i < titleNotif.length; i++) {
//
//            ModelNotification modelNotification1 = new ModelNotification();
//            modelNotification1.setTitleNotif(titleNotif[i]);
//            modelNotification1.setDetailNotif(detailNotif[i]);
//            modelNotification1.setTimeNotif(timeNotif[i]);
//            modelNotification1.setImageNotif(imageNotif[i]);
//            modelNotification.add(modelNotification1);
//        }

        return modelNotification;
    }

}
