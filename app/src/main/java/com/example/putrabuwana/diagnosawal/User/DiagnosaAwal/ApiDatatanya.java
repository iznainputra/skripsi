package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;


import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDatatanya {

//    String BASE_URL ="http://192.168.100.2/diagnosawal/index.php/api/RestAPI/";

    @GET("http://temporaltime.com/diagnosawal/index.php/api/RestAPI/screeningawal")
    //Call<List<DataDiagnoseawal>> getDatas();
//    Call<JSONresponse> getJSON();
    Call<ArrayList<DataDiagnoseawal>> LoadJSON();


    @GET("screeningawal")
    Call<ArrayList<DataDiagnoseawal>> getDetail(
            @Query("id") String id
    );



    @FormUrlEncoded
    @POST("screeningawal")
    Call<ResponseBody> createData(
            @Field("id") String id,
            @Field("deskripsi") String deskripsi
    );


    ///delete///
    @FormUrlEncoded
    @POST("delete")
    Call<ResponseBody> delete(@Field("id") String id);

    ///update///
    @FormUrlEncoded
    @POST("update")
    Call<ResponseBody> update(
            @Field("id") String id,
            @Field("deskripsi") String deskripsi

    );
}
