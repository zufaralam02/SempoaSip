package com.zufaralam02.sempoasip.Parent.Profil.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.iapps.libs.helpers.HTTPImb;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Contact extends BaseActivitySempoa {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setupNav("Contact");

//        HTTPImb httpImb = new HTTPImb(this, true) {
//            @Override
//            public String url() {
//                return "http://idokter.net/dokiapi/login_bridge";
//            }
//
//            @Override
//            public void onSuccess(JSONObject j) {
//                try {
//                    JSONObject jsonObject = j.getJSONObject("result");
//                    String name = jsonObject.getString("name");
//                    Toast.makeText(Contact.this, name, Toast.LENGTH_SHORT).show();
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
//        httpImb.setPostParams("id", "7791")
//                .setPostParams("passwd", "005mbx")
//                .setDisplayError(true)
//                .execute();

    }
}
