package com.example.putrabuwana.diagnosawal.User;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.ApiDatauser;
import com.example.putrabuwana.diagnosawal.Admin.DataUser.Datauser;
import com.example.putrabuwana.diagnosawal.R;

import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserFragment extends Fragment {

    SharedPreferences sharedpreferences;
    Button btn_edit;
    private ArrayList<Datauser> DataDA;
    ApiDatauser apiInterDU;
    TextView txno, txnama, txnotlp, txemail, txmplahir, txglahir, txalamat, txbb, txusername;
    String no, nama, no_tlp, email, tmp_lahir, tgl_lahir, alamat, berat_badan, username;
    public static final String TAG_NO = "no";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_NOTLP = "no_tlp";
    public final static String TAG_TMPLAHIR = "tmp_lahir";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_TGLLAHIR = "tgl_lahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_BERATBADAN = "berat_badan";
    public static final String TAG_USERNAME = "username";
    public static final String my_shared_preferences = "my_shared_preferences";
    public static final String URL = "http://localhost/diagnosawal/index.php/api/RestAPI/datauser/";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_user, container, false);

        sharedpreferences = getActivity().getSharedPreferences(UserFragment.my_shared_preferences, Context.MODE_PRIVATE);
        no = sharedpreferences.getString(TAG_NO,null);


        txno = (TextView) v.findViewById(R.id.txno);
        txnama = (TextView) v.findViewById(R.id.txnama);
        txnotlp = (TextView) v.findViewById(R.id.txnotlp);
        txemail = (TextView) v.findViewById(R.id.txemail);
        txmplahir = (TextView) v.findViewById(R.id.txmplahir);
        txglahir = (TextView) v.findViewById(R.id.txglahir);
        txalamat = (TextView) v.findViewById(R.id.txalamat);
        txglahir = (TextView) v.findViewById(R.id.txglahir);
        txalamat = (TextView) v.findViewById(R.id.txalamat);
        txbb = (TextView) v.findViewById(R.id.txbb);
        txusername = (TextView) v.findViewById(R.id.txusername);
        btn_edit = (Button) v.findViewById(R.id.btn_edit);
        txno.setText(no);
        v.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEdit();
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://temporaltime.com/diagnosawal/index.php/api/RestAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterDU = retrofit.create(ApiDatauser.class);
        LoadJSON();
        return v;
    }

    private void LoadJSON() {
        Call<ArrayList<Datauser>> call = apiInterDU.getDetail(String.valueOf(no));
        call.enqueue(new Callback<ArrayList<Datauser>>() {
            @Override
            public void onResponse(Call <ArrayList<Datauser>> call, Response<ArrayList<Datauser>> response) {
                if (response.isSuccessful()) {
                    ArrayList<Datauser> lister = response.body();
                    txno.setText(String.valueOf(lister.get(0).getNo()));
                    txnama.setText(lister.get(0).getNama());
                    txnotlp.setText(lister.get(0).getNo_tlp());
                    txemail.setText(lister.get(0).getEmail());
                    txmplahir.setText(lister.get(0).getTmp_lahir());
                    txglahir.setText(lister.get(0).getTgl_lahir());
                    txalamat.setText(lister.get(0).getAlamat());
                    txbb.setText(lister.get(0).getBerat_badan());
                    txusername.setText(lister.get(0).getUsername());
                    String nouse = txno.getText().toString();
                    String namause = txnama.getText().toString();
                    String tgl = txglahir.getText().toString();

                }
            }

            @Override
            public void onFailure(Call <ArrayList<Datauser>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });

    }
    private void userEdit(){
        Intent i = new Intent(getContext(), DetailDataClient.class);
        i.putExtra("no", txno.getText());
        i.putExtra("nama", txnama.getText());
        i.putExtra("no_tlp", txnotlp.getText());
        i.putExtra("email", txemail.getText());
        i.putExtra("tmp_lahir", txmplahir.getText());
        i.putExtra("tgl_lahir", txglahir.getText());
        i.putExtra("alamat", txalamat.getText());
        i.putExtra("berat_badan", txbb.getText());
        i.putExtra("username", txusername.getText());
        getContext().startActivity(i);
    }
}
