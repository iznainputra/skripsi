package com.example.putrabuwana.diagnosawal.Admin.DataUser;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDatauser {
    private static final String BASE_URL = "http://localhost/diagnosawal/index.php/api/RestAPI/";
    private static RetrofitDatauser mInstance;
    private Retrofit retrofit;

    private RetrofitDatauser(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized RetrofitDatauser getInstance(){
        if(mInstance == null){
            mInstance = new RetrofitDatauser();
        }
        return mInstance;
    }

    public ApiDatauser getApi() {
        return retrofit.create(ApiDatauser.class);
    }

}
