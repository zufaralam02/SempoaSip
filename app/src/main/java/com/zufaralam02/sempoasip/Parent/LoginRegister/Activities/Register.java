package com.zufaralam02.sempoasip.Parent.LoginRegister.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zufaralam02.sempoasip.ApiHelper.RetrofitClient;
import com.zufaralam02.sempoasip.Base.BaseActivitySempoa;
import com.zufaralam02.sempoasip.ApiHelper.BaseApiService;
import com.zufaralam02.sempoasip.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends BaseActivitySempoa {

    @BindView(R.id.edtNameRegister)
    TextInputEditText edtNameRegister;
    @BindView(R.id.edtEmailRegister)
    TextInputEditText edtEmailRegister;
    @BindView(R.id.edtPhoneRegister)
    TextInputEditText edtPhoneRegister;
    @BindView(R.id.edtPassRegister)
    TextInputEditText edtPassRegister;
    @BindView(R.id.btnRegisterRegister)
    Button btnRegisterRegister;
    @BindView(R.id.tvLoginRegister)
    TextView tvLoginRegister;

    Context context;
    BaseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        context = this;
        baseApiService = RetrofitClient.getClient().create(BaseApiService.class);

    }

    @OnClick({R.id.btnRegisterRegister, R.id.tvLoginRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegisterRegister:
//                startActivity(new Intent(context, AddChild.class));
                requestRegister();
                break;
            case R.id.tvLoginRegister:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;
        }
    }

    private void requestRegister() {
        String edtName = edtNameRegister.getText().toString();
        String edtEmail = edtEmailRegister.getText().toString();
        String edtPhone = edtPhoneRegister.getText().toString();
        String edtPass = edtPassRegister.getText().toString();
        if (edtName.isEmpty()) {
            Toast.makeText(context, "Nama Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (edtEmail.isEmpty()) {
            Toast.makeText(context, "Email Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (edtPhone.isEmpty()) {
            Toast.makeText(context, "Phone Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (edtPass.isEmpty()) {
            Toast.makeText(context, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else {
            baseApiService.registerRequest(edtNameRegister.getText().toString(),
                    edtEmailRegister.getText().toString(),
                    edtPhoneRegister.getText().toString(),
                    edtPassRegister.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("status_code").equals("1")) {
                                        String success = jsonObject.getString("status_message");
                                        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(context, Login.class));
                                    } else {
                                        String error = jsonObject.getString("status_message");
                                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {

                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        }
    }
}
