package com.example.putrabuwana.diagnosawal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class frgt_pass extends AppCompatActivity {
    TextView eml;
    EditText pass, repass;
    Button btn_newpass, btn_cncl;
    ApiForgotPass ApiInterFP;
    String mail, password, repassword;
    public static final String URL = "http://localhost/diagnosawal/index.php/api/RestAPI/updatedataus/";
    public final static String TAG_EMAIL = "email";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        mail = getIntent().getStringExtra("email");
        eml = (TextView) findViewById(R.id.eml);
        pass = (EditText) findViewById(R.id.pass);
        repass = (EditText) findViewById(R.id.repass);
        btn_newpass = (Button) findViewById(R.id.btn_newpass);
        btn_cncl = (Button) findViewById(R.id.btn_cncl);
        eml.setText(String.valueOf(mail));
        btn_newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updatePass();
            }
        });
        btn_cncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void updatePass(){
        String email = eml.getText().toString();
        password = pass.getText().toString();
        repassword = repass.getText().toString();
        if(password.isEmpty()){
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        if(password.length() < 6){
            pass.setError("Password should be at least 6 character");
            pass.requestFocus();
            return;
        }

        if(repassword.isEmpty()){
            repass.setError("Password is required");
            repass.requestFocus();
            return;
        }

        if(repassword.length() < 6){
            repass.setError("Password should be at least 6 character");
            repass.requestFocus();
            return;
        }

        if(!repassword .equals(password)){
            pass.setError("Password is not correct, Please check it again");
            repass.setError("Password is not correct, Please check it again");
            pass.requestFocus();
            return;
        }
//        Toast.makeText(frgt_pass.this, "email : " + email+ ", pass : " + password, Toast.LENGTH_SHORT).show();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterFP = retrofit.create(ApiForgotPass.class);
        Call<ResponseBody> call = ApiInterFP.update(email, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(frgt_pass.this, "Updated", Toast.LENGTH_SHORT).show();
                    Intent cancelIntent = new Intent(frgt_pass.this, MainActivity.class);
                    startActivity(cancelIntent);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }
}
