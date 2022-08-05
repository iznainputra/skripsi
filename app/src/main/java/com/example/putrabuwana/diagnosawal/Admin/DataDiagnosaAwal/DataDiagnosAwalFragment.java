package com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.ApiDatatanya;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DiagnosAwalFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataDiagnosAwalFragment extends Fragment {
    SharedPreferences sharedpreferencesadmin;
    String no, username;
    View v;
    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";
    public static final String my_shared_preferencesadmin = "my_shared_preferencesadmin";
    private RecyAdaptDataDiagnose myRecyAdapadmin;
    private RecyclerView myRecycleradmin;
    private ArrayList<DataDiagnoseawal> DataDA;
    ApiDatatanya apiInterface;
    FloatingActionButton fab;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragadmin_datadiagnosa, container, false);
        sharedpreferencesadmin = getActivity().getSharedPreferences(DataDiagnosAwalFragment.my_shared_preferencesadmin,Context.MODE_PRIVATE);
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
        apiInterface = retrofit.create(ApiDatatanya.class);
        myRecycleradmin = (RecyclerView) v.findViewById(R.id.diagawaladmin_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        myRecycleradmin.setLayoutManager(layoutManager);
        myRecycleradmin.setHasFixedSize(true);
        fab = v.findViewById(R.id.newdata);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity(),  " ADD NEW DATA ", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(getActivity(), AddDataDiagnos.class));
            }
        });
        LoadJSON();
    }

    private void LoadJSON() {
        Call<ArrayList<DataDiagnoseawal>> call = apiInterface.LoadJSON();
        call.enqueue(new Callback<ArrayList<DataDiagnoseawal>>() {

            @Override
            public void onResponse(Call <ArrayList<DataDiagnoseawal>> call, Response<ArrayList<DataDiagnoseawal>> response) {
                DataDA = response.body();
                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
                myRecyAdapadmin = new RecyAdaptDataDiagnose(getActivity(),DataDA);
                myRecycleradmin.setAdapter(myRecyAdapadmin);
                myRecyAdapadmin.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call <ArrayList<DataDiagnoseawal>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

}