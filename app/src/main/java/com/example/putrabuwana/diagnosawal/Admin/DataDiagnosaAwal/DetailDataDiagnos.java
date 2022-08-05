package com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.DetailDatauser;
import com.example.putrabuwana.diagnosawal.Admin.MenuAdmin;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.ApiDatatanya;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailDataDiagnos extends AppCompatActivity {
    EditText editid;
    EditText editdeskripsi;
    Button btn_del;
    Button btn_upd;
    Button btn_yes;
    Button btn_no;
    public static final String URL = "http://localhost/diagnosawal/index.php/api/RestAPI/screeningedit/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragadmin_detaildiagnos);
        Intent intent = getIntent();

        editid = (EditText) findViewById(R.id.editid);
        editdeskripsi = (EditText) findViewById(R.id.editdeskripsi);
        btn_upd = (Button) findViewById(R.id.btn_upd);
        btn_del = (Button) findViewById(R.id.btn_del);


        findViewById(R.id.btn_upd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiagUpdate();
            }
        });
        findViewById(R.id.btn_del).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast toast2 = Toast.makeText(getApplicationContext(), "DELETE", Toast.LENGTH_SHORT);
//                toast2.show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailDataDiagnos.this);
                final View mView = getLayoutInflater().inflate(R.layout.alertdialog_delete,null);
                btn_yes = (Button) mView.findViewById(R.id.btn_yes);
                btn_no = (Button) mView.findViewById(R.id.btn_no);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DiagDelete();
                        dialog.dismiss();
                    }
                });
                btn_no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast toast = Toast.makeText(getApplicationContext(), "NO", Toast.LENGTH_SHORT);
                        toast.show();


                    }
                });
            }
        });

        String id = getIntent().getStringExtra("id");
        String deskripsi = getIntent().getStringExtra("deskripsi");



        editid.setText(String.valueOf(id));
        editdeskripsi.setText(deskripsi);
    }
    private void DiagUpdate() {
        final String id = editid.getText().toString().trim();
        String deskripsi = editdeskripsi.getText().toString().trim();


        if(id.isEmpty()){
            editid.setError("Id is required");
            editid.requestFocus();
            return;
        }

        if(deskripsi.isEmpty()){
            editdeskripsi.setError("Deskripsi is required");
            editdeskripsi.requestFocus();
            return;
        }
//        Toast toast = Toast.makeText(getApplicationContext(), id +","+ deskripsi +"Updated" , Toast.LENGTH_SHORT);
//        toast.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
               .build();
        ApiDatatanya api = retrofit.create(ApiDatatanya.class);
        Call<ResponseBody> call = api.update(id, deskripsi);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast toast = Toast.makeText(getApplicationContext(),  id+" UPDATED", Toast.LENGTH_SHORT);
                toast.show();
                Intent intent = new Intent(getApplicationContext(), MenuAdmin.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),  " FAILED ", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }


    private void DiagDelete(){
        final String id = editid.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost/diagnosawal/index.php/api/RestAPI/screeningdelete/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDatatanya api = retrofit.create(ApiDatatanya.class);
        Call<ResponseBody> call = api.delete(id);
        call.enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(DetailDataDiagnos.this,id+" Deleted",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), MenuAdmin.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailDataDiagnos.this,"Failed",Toast.LENGTH_LONG).show();

            }
        });



    }
}
