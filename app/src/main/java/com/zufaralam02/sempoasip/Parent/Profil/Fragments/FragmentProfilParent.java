package com.zufaralam02.sempoasip.Parent.Profil.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zufaralam02.sempoasip.Parent.LoginRegister.Activities.Login;
import com.zufaralam02.sempoasip.Parent.Profil.Activities.AccountSetting;
import com.zufaralam02.sempoasip.Parent.Profil.Activities.Contact;
import com.zufaralam02.sempoasip.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfilParent extends Fragment {
    CardView cardAccountSetting, cardContact, cardLogout;

    public FragmentProfilParent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil_parent, container, false);

        cardAccountSetting = view.findViewById(R.id.cardAccountSetting);
        cardAccountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AccountSetting.class));

            }
        });

        cardContact = view.findViewById(R.id.cardContact);
        cardContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Contact.class));
            }
        });

        cardLogout = view.findViewById(R.id.cardLogout);
        cardLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Login.class));
            }
        });

        return view;
    }

}
