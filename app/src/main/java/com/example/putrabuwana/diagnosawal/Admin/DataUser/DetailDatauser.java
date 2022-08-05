package com.example.putrabuwana.diagnosawal.Admin.DataUser;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
//import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.putrabuwana.diagnosawal.Admin.MenuAdmin;
import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.MySingleton;
import com.example.putrabuwana.diagnosawal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

public class DetailDatauser extends AppCompatActivity {
    EditText editno;
    EditText editnama;
    EditText editno_tlp;
    EditText editemail;
    EditText edittgl_lahir;
    EditText edittmp_lahir;
    EditText editalamat;
    EditText editberat_badan;
    EditText editusername;
    EditText editpassword;
    EditText editkonf_pass;
    Button btn_delete;
    Button btn_update;
    Button btn_yes;
    Button btn_no;
    DatePickerDialog.OnDateSetListener setListener;
    public static final String URL = "http://localhost/diagnosawal/index.php/api/RestAPI/datauser/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragadmin_updatauser);
        Intent intent = getIntent();

        editno = (EditText)findViewById(R.id.editno);
        editnama = (EditText)findViewById(R.id.editnama);
        editno_tlp = (EditText)findViewById(R.id.editno_tlp);
        editemail = (EditText)findViewById(R.id.editemail);
        edittmp_lahir = (EditText)findViewById(R.id.edittmp_lahir);
        edittgl_lahir = (EditText)findViewById(R.id.edittgl_lahir);
        editalamat = (EditText)findViewById(R.id.editalamat);
        editberat_badan = (EditText)findViewById(R.id.editberat_badan);
        editusername = (EditText)findViewById(R.id.editusername);
        editpassword = (EditText)findViewById(R.id.editpassword);
        editkonf_pass = (EditText)findViewById(R.id.editkonf_pass);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edittgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailDatauser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        edittgl_lahir.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userUpdate();
            }
        });
        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast2 = Toast.makeText(getApplicationContext(), "DELETE", Toast.LENGTH_SHORT);
                toast2.show();
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(DetailDatauser.this);
                final View mView = getLayoutInflater().inflate(R.layout.alertdialog_delete,null);
                btn_yes = (Button) mView.findViewById(R.id.btn_yes);
                btn_no = (Button) mView.findViewById(R.id.btn_no);
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                btn_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        userDelete();
                        dialog.dismiss();
//                        final String no = editno.getText().toString().trim();
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl(URL)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//                        ApiDatauser api = retrofit.create(ApiDatauser.class);
//                        Call<ResponseBody> call = api.delete(no);
//                        Toast toast1 = Toast.makeText(getApplicationContext(), no+" , YES", Toast.LENGTH_SHORT);
//                        toast1.show();
//                        call.enqueue(new Callback<ResponseBody>() {
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                Toast.makeText(DetailDatauser.this,"Deleted",Toast.LENGTH_LONG).show();
//                                dialog.dismiss();
////                                Intent ints = new Intent(DetailDatauser.this, DataUserFragment.class);
////                                startActivity(ints);
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Toast.makeText(DetailDatauser.this,"Failed",Toast.LENGTH_LONG).show();
//                                dialog.dismiss();
//                            }
//                        });
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

        String no = getIntent().getStringExtra("no");
        String nama = getIntent().getStringExtra("nama");
        String no_tlp = getIntent().getStringExtra("no_tlp");
        String email = getIntent().getStringExtra("email");
        String tmp_lahir = getIntent().getStringExtra("tmp_lahir");
        String tgl_lahir = getIntent().getStringExtra("tgl_lahir");
        String alamat = getIntent().getStringExtra("alamat");
        String berat_badan = getIntent().getStringExtra("berat_badan");
        String username = getIntent().getStringExtra("username");


        editno.setText(String.valueOf(no));
        editnama.setText(nama);
        editno_tlp.setText(no_tlp);
        editemail.setText(email);
        edittmp_lahir.setText(tmp_lahir);
        edittgl_lahir.setText(tgl_lahir);
        editalamat.setText(alamat);
        editberat_badan.setText(berat_badan);
        editusername.setText(username);
    }
//
    private void userUpdate(){
        final String no = editno.getText().toString().trim();
        String nama = editnama.getText().toString().trim();
        String no_tlp = editno_tlp.getText().toString().trim();
        String email = editemail.getText().toString().trim();
        String tmp_lahir= edittmp_lahir.getText().toString().trim();
        String tgl_lahir = edittgl_lahir.getText().toString().trim();
        String alamat = editalamat.getText().toString().trim();
        String berat_badan = editberat_badan.getText().toString().trim();
        String username = editusername.getText().toString().trim();
        String password = editpassword.getText().toString().trim();
        String konfpass = editkonf_pass.getText().toString().trim();

        if(nama.isEmpty()){
            editnama.setError("Nama is required");
            editnama.requestFocus();
            return;
        }

        if(no_tlp.isEmpty()){
            editno_tlp.setError("No.Tlp is required");
            editno_tlp.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editemail.setError("Fill in a valid email");
            editemail.requestFocus();
            return;
        }

        if(tmp_lahir.isEmpty()){
            edittmp_lahir.setError("Tempat Lahir is required");
            edittmp_lahir.requestFocus();
            return;
        }

        if(tgl_lahir.isEmpty()){
            edittgl_lahir.setError("Tanggal Lahir is required");
            edittgl_lahir.requestFocus();
            return;
        }

        if(alamat.isEmpty()){
            editalamat.setError("Alamat is required");
            editalamat.requestFocus();
            return;
        }

        if(berat_badan.isEmpty()){
            editberat_badan.setError("Berat Badan is required");
            editberat_badan.requestFocus();
            return;
        }


        if(username.isEmpty()){
            editusername.setError("Username is required");
            editusername.requestFocus();
            return;
        }


        if(password.isEmpty()){
            editpassword.setError("Password is required");
            editpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editpassword.setError("Password should be at least 6 character");
            editpassword.requestFocus();
            return;
        }

        if(konfpass.isEmpty()){
            editkonf_pass.setError("Password is required");
            editkonf_pass.requestFocus();
            return;
        }

        if(konfpass.length() < 6){
            editkonf_pass.setError("Password should be at least 6 character");
            editkonf_pass.requestFocus();
            return;
        }

        if(!konfpass .equals(password)){
            editpassword.setError("Password is not correct, Please check it again");
            editkonf_pass.setError("Password is not correct, Please check it again");
            editpassword.requestFocus();
            return;
        }

//        Toast toast = Toast.makeText(getApplicationContext(),  no+" "+nama+" "+no_tlp+" "+email+" "+tmp_lahir+" "+tgl_lahir+" "+alamat+" "+berat_badan+" "+username+" "+password, Toast.LENGTH_SHORT);
//        toast.show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDatauser api = retrofit.create(ApiDatauser.class);
        Call<ResponseBody> call = api.update(no, nama, no_tlp, email, tmp_lahir, tgl_lahir, alamat, berat_badan, username, password);

       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               Toast toast = Toast.makeText(getApplicationContext(),  no+" UPDATED", Toast.LENGTH_SHORT);
               toast.show();
               startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Toast toast = Toast.makeText(getApplicationContext(),  no+" FAILED", Toast.LENGTH_SHORT);
               toast.show();
           }
       });

    }
    private void userDelete(){
        final String no = editno.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://temporaltime.com/diagnosawal/index.php/api/RestAPI/dtuser/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDatauser api = retrofit.create(ApiDatauser.class);
        Call<ResponseBody> call = api.delete(no);
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(DetailDatauser.this,no+" Deleted",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailDatauser.this,"Failed",Toast.LENGTH_LONG).show();

            }
        });



    }


//    public void ubahprofile () {
//        final String no = editno.getText().toString();
//        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, URL_DELETE,
//                new Response.Listener<String>() {
//                    public void onResponse(String response) {
//                        Log.d("json", response.toString());
//
//                        Toast.makeText(DetailDatauser.this,""+no,Toast.LENGTH_LONG).show();
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//
//            }
//        }) {
//            @Override
//            public String getBodyContentType() {
//                return "application/x-www-form-urlen    coded; charset=UTF-8";
//            }
//
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String, String> params = new HashMap<>();
//                params.put("no", no);
//                return params;
//            }
//
//        };
//        MySingleton.getInstance(this).addToRequestQueue(stringRequest);
//
//    }


}
