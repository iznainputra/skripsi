package com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.MenuAdmin;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.ApiDatatanya;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDataDiagnos extends AppCompatActivity {

    EditText textid;
    EditText textdeskripsi;
    Button btn_save;
    Button btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_diagnos);

        textid = (EditText)findViewById(R.id.textid);
        textdeskripsi = (EditText)findViewById(R.id.textdeskripsi);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiagSave();
            }
        });
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
            }
         });
    }

    private void DiagSave(){
        String id = textid.getText().toString().trim();
        String deskripsi = textdeskripsi.getText().toString().trim();

        if(id.isEmpty()){
            textid.setError("Id is required");
            textid.requestFocus();
            return;
        }

        if(deskripsi.isEmpty()){
            textdeskripsi.setError("Deskripsi is required");
            textdeskripsi.requestFocus();
            return;
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/diagnosawal/index.php/api/RestAPI/screeningawal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDatatanya api = retrofit.create(ApiDatatanya.class);
        Call<ResponseBody> call = api.createData(id, deskripsi);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast toast = Toast.makeText(getApplicationContext(),  " DATA IS ADDED", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),  " DATA FAILED ADDED", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
