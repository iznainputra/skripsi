package com.example.putrabuwana.diagnosawal.Admin.DataUser;

import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiDatauser {
    @GET("http://localhost/diagnosawal/index.php/api/RestAPI/datauser")
    Call<ArrayList<Datauser>> LoadJSON();

    @GET("datauserone")
    Call<ArrayList<Datauser>> getDetail(
            @Query("no") String no
    );


    @FormUrlEncoded
    @POST("create")
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

    @FormUrlEncoded
    @POST("delete")
    Call<ResponseBody> delete(@Field("no") String no);

    @FormUrlEncoded
    @POST("update")
    Call<ResponseBody> update(
            @Field("no") String no,
            @Field("nama") String nama,
            @Field("no_tlp") String no_tlp,
            @Field("email") String email,
            @Field("tmp_lahir") String tmp_lahir,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("alamat") String alamat,
            @Field("berat_badan") String berat_badan,
            @Field("username") String username,
            @Field("password") String password
            );
}
