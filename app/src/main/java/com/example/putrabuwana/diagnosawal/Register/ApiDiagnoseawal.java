package com.example.putrabuwana.diagnosawal.Register;


import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiDiagnoseawal {

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> createUser(
            @Field("nama") String nama,
            @Field("no_tlp") String no_tlp,
            @Field("email") String email,
            @Field("tmp_lahir") String tmp_lahir,
            @Field("tgl_lahir") String  tgl_lahir,
            @Field("alamat") String alamat,
            @Field("berat_badan") String berat_badan,
            @Field("username") String username,
            @Field("password") String password
            );
}
