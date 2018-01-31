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
import com.zufaralam02.sempoasip.Parent.BottomNavigation.BottomNavigationParent;
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

public class Login extends BaseActivitySempoa {

    @BindView(R.id.edtEmailLogin)
    TextInputEditText edtEmailLogin;
    @BindView(R.id.edtPassLogin)
    TextInputEditText edtPassLogin;
    @BindView(R.id.tvForgotPassword)
    TextView tvForgotPassword;
    @BindView(R.id.btnLoginLogin)
    Button btnLoginLogin;
    @BindView(R.id.btnRegisterLogin)
    Button btnRegisterLogin;

    Context context;
    BaseApiService baseApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        context = this;
        baseApiService = RetrofitClient.getClient().create(BaseApiService.class);

    }

    @OnClick({R.id.tvForgotPassword, R.id.btnLoginLogin, R.id.btnRegisterLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvForgotPassword:
                startActivity(new Intent(getApplicationContext(), ForgotPassword.class));
                break;
            case R.id.btnLoginLogin:
//                startActivity(new Intent(getApplicationContext(), BottomNavigationParent.class));
                requestLogin();
                break;
            case R.id.btnRegisterLogin:
                startActivity(new Intent(getApplicationContext(), Register.class));
                break;
        }
    }

    private void requestLogin() {
        String edtEmail = edtEmailLogin.getText().toString();
        String edtPass = edtPassLogin.getText().toString();
        if (edtEmail.isEmpty()) {
            Toast.makeText(context, "Email Harus Diisi", Toast.LENGTH_SHORT).show();
        } else if (edtPass.isEmpty()) {
            Toast.makeText(context, "Password Harus Diisi", Toast.LENGTH_SHORT).show();
        } else {
            baseApiService.loginRequest(edtEmailLogin.getText().toString(),
                    edtPassLogin.getText().toString())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response.body().string());
                                    if (jsonObject.getString("status_code").equals("1")) {
                                        String success = jsonObject.getString("status_message");
                                        Toast.makeText(context, success, Toast.LENGTH_SHORT).show();
                                        String fullName = jsonObject.getJSONObject("result").getString("parent_fullname");
                                        String email = jsonObject.getJSONObject("result").getString("parent_email");
                                        String hp = jsonObject.getJSONObject("result").getString("parent_hp_nr");
                                        String pwd = jsonObject.getJSONObject("result").getString("parent_pwd");
                                        Intent intent = new Intent(context, BottomNavigationParent.class);
                                        intent.putExtra("resultName", fullName);
                                        intent.putExtra("resultEmail", email);
                                        intent.putExtra("resultHp", hp);
                                        intent.putExtra("resultPwd", pwd);
                                        startActivity(intent);
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
