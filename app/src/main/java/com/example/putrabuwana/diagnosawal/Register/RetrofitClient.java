package com.example.putrabuwana.diagnosawal.Register;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.ApiDatauser;
import com.example.putrabuwana.diagnosawal.Admin.DataUser.DetailDatauser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://localhost/diagnosawal/index.php/api/RestAPI/register/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public ApiDiagnoseawal getApi() {
        return retrofit.create(ApiDiagnoseawal.class);
    }


}
