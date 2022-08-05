
package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.profile;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class DiagnosAwalFragment extends Fragment {
    View v;
    Button btn_mulai;
//    private RecyclerAdaptDiagnoseawal myReAdapt;
//    private RecyclerView myRecyclerv;
    private ArrayList<DataDiagnoseawal> DataDA;
    ApiDatatanya apiInterface;
    TextView t_no, nm, bb;
    String no, nma, bbe;
    SharedPreferences sharedpreferences;
    public static final String TAG_NO = "no";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_BERATBADAN = "berat_badan";
    public static final String my_shared_preferences = "my_shared_preferences";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_diagnosawal, container, false);
        t_no = (TextView) v.findViewById(R.id.t_no);
        nm = (TextView) v.findViewById(R.id.nm);
        bb = (TextView) v.findViewById(R.id.bb);

        sharedpreferences = getActivity().getSharedPreferences(DiagnosAwalFragment.my_shared_preferences, Context.MODE_PRIVATE);
        no = sharedpreferences.getString(TAG_NO,null);
        nma = sharedpreferences.getString(TAG_NAMA,null);
        bbe = sharedpreferences.getString(TAG_BERATBADAN,null);
        t_no.setText(no);
        nm.setText(nma);
        bb.setText(bbe);
        initViews();


        return v;
    }

    private void initViews() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/diagnosawal/index.php/api/RestAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiDatatanya.class);
//        myRecyclerv = (RecyclerView) v.findViewById(R.id.diagawal_recycler);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        myRecyclerv.setLayoutManager(layoutManager);
//        myRecyclerv.setHasFixedSize(true);
        btn_mulai = v.findViewById(R.id.btn_mulai);
        btn_mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String no = t_no.getText().toString();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://localhost/diagnosawal/index.php/api/RestAPI/datascreenuser/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                ApiDataScreenUser api = retrofit.create(ApiDataScreenUser.class);
                Call<ResponseBody> call = api.createData(no);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Toast toast = Toast.makeText(getActivity(),  " Please answer all questions according to your current situation for each question ", Toast.LENGTH_SHORT);
                        toast.show();
                        startActivity(new Intent(getContext(), MulaiDiagnose.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast toast = Toast.makeText(getActivity(),  " FAILED ", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });

            }
        });
//        LoadJSON();
    }

//    private void LoadJSON() {
//        Call<ArrayList<DataDiagnoseawal>> call = apiInterface.LoadJSON();
//        call.enqueue(new Callback <ArrayList<DataDiagnoseawal>>() {
//
//            @Override
//            public void onResponse(Call <ArrayList<DataDiagnoseawal>> call, Response <ArrayList<DataDiagnoseawal>> response) {
//                DataDA = response.body();
//                Log.i(MainActivity.class.getSimpleName(), response.body().toString());
//                myReAdapt = new RecyclerAdaptDiagnoseawal(DataDA);
//                myRecyclerv.setAdapter(myReAdapt);
//                myReAdapt.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call <ArrayList<DataDiagnoseawal>> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//            }
//        });
//    }
}


