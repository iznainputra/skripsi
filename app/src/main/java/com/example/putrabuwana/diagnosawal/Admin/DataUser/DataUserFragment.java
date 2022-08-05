package com.example.putrabuwana.diagnosawal.Admin.DataUser;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal.AddDataDiagnos;
import com.example.putrabuwana.diagnosawal.Admin.HomeAdminFragment;
import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.ApiDatatanya;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.RecyclerAdaptDiagnoseawal;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataUserFragment extends Fragment {

    SharedPreferences sharedpreferencesadmin;
    TextView txt_no, txt_username;
    String no, username;
    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";

    View v;
    private RecyclerAdaptDatauser myReAdapt;
    private RecyclerView myRecyclerv;
    private ArrayList<Datauser> DataDU;
    FloatingActionButton fab;
    ApiDatauser apiInterface;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragadmin_datauser, container, false);
        sharedpreferencesadmin = getActivity().getSharedPreferences(HomeAdminFragment.my_shared_preferencesadmin, Context.MODE_PRIVATE);
        no =sharedpreferencesadmin.getString(TAG_NO,null);
        username =sharedpreferencesadmin.getString(TAG_USERNAME,null);

        initViews();


        return v;


    }
    private void initViews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/diagnosawal/index.php/api/RestAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiDatauser.class);
        myRecyclerv = (RecyclerView) v.findViewById(R.id.diagawal_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecyclerv.setLayoutManager(layoutManager);
        myRecyclerv.setHasFixedSize(true);
        fab = v.findViewById(R.id.datanew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),  " ADD NEW DATA ", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(getActivity(), AddDataUser.class));
            }
        });
        LoadJSON();
    }

    private void LoadJSON() {
        Call<ArrayList<Datauser>> call = apiInterface.LoadJSON();
        call.enqueue(new Callback<ArrayList<Datauser>>() {

            @Override
            public void onResponse(Call <ArrayList<Datauser>> call, Response<ArrayList<Datauser>> response) {
                DataDU = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                myReAdapt = new RecyclerAdaptDatauser(getActivity(),DataDU);
                myRecyclerv.setAdapter(myReAdapt);
                myReAdapt.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call <ArrayList<Datauser>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}
