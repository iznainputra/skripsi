package com.example.putrabuwana.diagnosawal;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiForgotPass {
    @GET("datausbymail")
    Call<ArrayList<DataForgotUser>> getMail(
            @Query("email") String email
    );

    @FormUrlEncoded
    @POST("update")
    Call<ResponseBody> update(
            @Field("email") String email,
            @Field("password") String password
    );
}
