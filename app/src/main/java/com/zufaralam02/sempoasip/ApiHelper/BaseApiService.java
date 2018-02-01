package com.zufaralam02.sempoasip.ApiHelper;

import com.zufaralam02.sempoasip.Parent.Notification.Models.ModelNotificationn;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by user on 26/01/2018.
 */

public interface BaseApiService {

    @FormUrlEncoded
    @POST("loginParent")
    Call<ResponseBody> loginRequest(@Field("parent_email") String parentEmail,
                                    @Field("parent_pwd") String parentPwd);

    @FormUrlEncoded
    @POST("registerParent")
    Call<ResponseBody> registerRequest(@Field("parent_fullname") String parentFullName,
                                       @Field("parent_email") String parentEmail,
                                       @Field("parent_hp_nr") String parentHpNr,
                                       @Field("parent_pwd") String parentPwd);

//    @POST("getNotificationByID")
//    Call<ModelNotificationn> getNotification(@Query("parent_id") String parentId);

//    @GET("movie/top_rated")
//    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
//    @GET("movie/{id}")
//    Call<MoviesResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
