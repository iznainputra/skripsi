package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiDataScreenUser {

    @FormUrlEncoded
    @POST("datascreenuser")
    Call<ResponseBody> createData(
            @Field("no") String no

    );

    @FormUrlEncoded
    @POST("fuzzy")
    Call<ResponseBody> fuzzyInput(
            @Field("no") String no,
            @Field("fuzzy_depr") String fuzzy_depr,
            @Field("fuzzy_bb") String fuzzy_bb,
            @Field("pred_max") String pred_max,
            @Field("zi") String  zi
    );
    @FormUrlEncoded
    @POST("update")
    Call<ResponseBody> update(
            @Field("no") String no,
            @Field("s1") Integer s1,
            @Field("s2") Integer s2,
            @Field("s3") Integer s3,
            @Field("s4") Integer s4,
            @Field("s5") Integer s5,
            @Field("s6") Integer s6,
            @Field("s7") Integer s7,
            @Field("s8") Integer s8,
            @Field("s9") Integer s9,
            @Field("s10") Integer s10,
            @Field("s11") Integer s11,
            @Field("s12") Integer s12,
            @Field("s13") Integer s13,
            @Field("s14") Integer s14,
            @Field("s15") Integer s15,
            @Field("s16") Integer s16,
            @Field("s17") Integer s17,
            @Field("total_gejala") Integer total_gejala

    );
}
