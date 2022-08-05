package com.example.putrabuwana.diagnosawal.User;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.ApiDatauser;
import com.example.putrabuwana.diagnosawal.R;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailDataClient extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText editno, editnama, editno_tlp, editemail, edittmp_lahir, edittgl_lahir, editalamat, editberat_badan, editusername, editpassword, editkonf_pass;
    Button btn_save;
    Button btn_cancel;
    DatePickerDialog.OnDateSetListener setListener;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildata);
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
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        edittgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        DetailDataClient.this, new DatePickerDialog.OnDateSetListener() {
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

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datauserUpdate();
            }
        });

        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
        no = getIntent().getStringExtra("no");
        nama = getIntent().getStringExtra("nama");
        no_tlp = getIntent().getStringExtra("no_tlp");
        email = getIntent().getStringExtra("email");
        tmp_lahir = getIntent().getStringExtra("tmp_lahir");
        tgl_lahir = getIntent().getStringExtra("tgl_lahir");
        alamat = getIntent().getStringExtra("alamat");
        berat_badan = getIntent().getStringExtra("berat_badan");
        username = getIntent().getStringExtra("username");



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
    private void datauserUpdate(){
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
            editemail.setError("fill in a valid email");
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
                Toast toast = Toast.makeText(getApplicationContext(),  "SAVED", Toast.LENGTH_SHORT);
                toast.show();
                startActivity(new Intent(getApplicationContext(), Menu.class));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),  "FAILED", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

    }
}
