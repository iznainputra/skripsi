package com.example.putrabuwana.diagnosawal.Admin.DataUser;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.MenuAdmin;
import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddDataUser extends AppCompatActivity implements View.OnClickListener {
    EditText mTextnama;
    EditText mTextnotlp;
    EditText mTextemail;
    EditText mTexttmplahir;
    EditText mTexttgllahir;
    EditText mTextalamat;
    EditText mTextpassword;
    EditText mtextberabadan;
    EditText mTextusername;
    EditText mTextkonfpass;
    DatePickerDialog.OnDateSetListener setListener;
    Button btn_save;
    Button btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data_user);
        mTextnama = (EditText)findViewById(R.id.nama);
        mTextnotlp = (EditText)findViewById(R.id.no_tlp);
        mTextemail = (EditText)findViewById(R.id.email);
        mTexttmplahir = (EditText)findViewById(R.id.tmp_lahir);
        mTexttgllahir = (EditText)findViewById(R.id.tgl_lahir);
        mTextalamat = (EditText)findViewById(R.id.alamat);
        mtextberabadan = (EditText)findViewById(R.id.berat_badan);
        mTextusername = (EditText)findViewById(R.id.username);
        mTextpassword = (EditText)findViewById(R.id.password);
        mTextkonfpass = (EditText)findViewById(R.id.konf_pass);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mTexttgllahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        AddDataUser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        String date = day+"/"+month+"/"+year;
                        mTexttgllahir.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    private void Adduser(){
        String nama = mTextnama.getText().toString().trim();
        String no_tlp = mTextnotlp.getText().toString().trim();
        String email = mTextemail.getText().toString().trim();
        String tmp_lahir= mTexttmplahir.getText().toString().trim();
        String tgl_lahir = mTexttgllahir.getText().toString().trim();
        String alamat = mTextalamat.getText().toString().trim();
        String berat_badan = mtextberabadan.getText().toString().trim();
        String username = mTextusername.getText().toString().trim();
        String password = mTextpassword.getText().toString().trim();
        String konfpass = mTextkonfpass.getText().toString().trim();

        if(nama.isEmpty()){
            mTextnama.setError("Nama is required");
            mTextnama.requestFocus();
            return;
        }

        if(no_tlp.isEmpty()){
            mTextnotlp.setError("No.Tlp is required");
            mTextnotlp.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mTextemail.setError("Email a valid email");
            mTextemail.requestFocus();
            return;
        }

        if(tmp_lahir.isEmpty()){
            mTexttmplahir.setError("Tempat Lahir is required");
            mTexttmplahir.requestFocus();
            return;
        }

        if(tgl_lahir.isEmpty()){
            mTexttgllahir.setError("Tanggal Lahir is required");
            mTexttgllahir.requestFocus();
            return;
        }

        if(alamat.isEmpty()){
            mTextalamat.setError("Alamat is required");
            mTextalamat.requestFocus();
            return;
        }

        if(berat_badan.isEmpty()){
            mtextberabadan.setError("Berat Badan is required");
            mtextberabadan.requestFocus();
            return;
        }


        if(username.isEmpty()){
            mTextusername.setError("Username is required");
            mTextusername.requestFocus();
            return;
        }


        if(password.isEmpty()){
            mTextpassword.setError("Password is required");
            mTextpassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            mTextpassword.setError("Password should be at least 6 character");
            mTextpassword.requestFocus();
            return;
        }

        if(konfpass.isEmpty()){
            mTextkonfpass.setError("Password is required");
            mTextkonfpass.requestFocus();
            return;
        }

        if(konfpass.length() < 6){
            mTextkonfpass.setError("Password should be at least 6 character");
            mTextkonfpass.requestFocus();
            return;
        }

        if(!konfpass .equals(password)){
            mTextpassword.setError("Password is not correct, Please check it again");
            mTextkonfpass.setError("Password is not correct, Please check it again");
            mTextpassword.requestFocus();
            return;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://temporaltime.com/diagnosawal/index.php/api/RestAPI/register/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDatauser api = retrofit.create(ApiDatauser.class);
        Call<ResponseBody> call = api.createUser(nama, no_tlp, email, tmp_lahir, tgl_lahir, alamat, berat_badan, username, password);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    mTextnama.setText("");
                    mTextnotlp.setText("");
                    mTextemail.setText("");
                    mTextalamat.setText("");
                    mTexttmplahir.setText("");
                    mTexttgllahir.setText("");
                    mtextberabadan.setText("");
                    mTextusername.setText("");
                    mTextpassword.setText("");
                    mTextkonfpass.setText("");
                    String s = response.body().string();
                    Toast.makeText(AddDataUser.this, "New data has been entered",Toast.LENGTH_LONG).show();
                    Intent cancelIntent = new Intent(AddDataUser.this, MenuAdmin.class);
                    startActivity(cancelIntent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AddDataUser.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                Adduser();
                break;
            case R.id.btn_cancel:
                Intent cancelIntent = new Intent(AddDataUser.this, MenuAdmin.class);
                startActivity(cancelIntent);
                break;
        }
    }
}
