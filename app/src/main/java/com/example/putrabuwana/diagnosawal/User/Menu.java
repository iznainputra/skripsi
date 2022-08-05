package com.example.putrabuwana.diagnosawal.User;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DiagnosAwalFragment;

public class Menu extends AppCompatActivity  {

    Button btn_logout;
    TextView txt_no, txt_nama, txt_no_tlp, txt_email, txt_tmp_lahir, txt_tgl_lahir, txt_alamat, txt_berat_badan, txt_username;
    String no, username, nama, no_tlp, email, tmp_lahir, tgl_lahir, alamat, berat_badan;
    SharedPreferences sharedpreferences;

    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";
    public final static String TAG_NAMA = "nama";
    public final static String TAG_NOTLP = "no_tlp";
    public final static String TAG_TMPLAHIR = "tmp_lahir";
    public final static String TAG_EMAIL = "email";
    public final static String TAG_TGLLAHIR = "tgl_lahir";
    public final static String TAG_ALAMAT = "alamat";
    public final static String TAG_BERATBADAN = "berat_badan";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        txt_no = (TextView) findViewById(R.id.txt_no);
        txt_nama = (TextView) findViewById(R.id.txt_nama);
        txt_no_tlp = (TextView) findViewById(R.id.txt_no_tlp);
        txt_email = (TextView) findViewById(R.id.txt_email);
        txt_tmp_lahir = (TextView) findViewById(R.id.txt_tmp_lahir);
        txt_tgl_lahir = (TextView) findViewById(R.id.txt_tgl_lahir);
        txt_alamat = (TextView) findViewById(R.id.txt_alamat);
        txt_berat_badan = (TextView) findViewById(R.id.txt_berat_badan);
        txt_username = (TextView) findViewById(R.id.txt_username);
//        btn_logout = (Button) findViewById(R.id.navigation_logout);

        sharedpreferences = getSharedPreferences(MainActivity.my_shared_preferences, Context.MODE_PRIVATE);

        no = getIntent().getStringExtra(TAG_NO);
        nama = getIntent().getStringExtra(TAG_NAMA);
        no_tlp = getIntent().getStringExtra(TAG_NOTLP);
        email = getIntent().getStringExtra(TAG_EMAIL);
        tmp_lahir = getIntent().getStringExtra(TAG_TMPLAHIR);
        tgl_lahir = getIntent().getStringExtra(TAG_TGLLAHIR);
        alamat = getIntent().getStringExtra(TAG_ALAMAT);
        berat_badan = getIntent().getStringExtra(TAG_BERATBADAN);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_no.setText("ID : " + no);
        txt_nama.setText("nama : " + nama);
        txt_no_tlp.setText("no tlp : " + no_tlp);
        txt_alamat.setText("alamat : " + alamat);
        txt_email.setText("email : " + email);
        txt_tmp_lahir.setText("tempat lahir : " + tmp_lahir);
        txt_tgl_lahir.setText("tgl lahir : " + tgl_lahir);
        txt_alamat.setText("alamat : " + alamat);
        txt_berat_badan.setText("berat badan : " + berat_badan);
        txt_username.setText("USERNAME : " + username);
        BottomNavigationView navView = findViewById(R.id.bottom_nav_menu);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                new HomeFragment()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.navigation_user:
                            fragment = new UserFragment();
                            break;

                        case R.id.navigation_diagnose:
                            fragment = new DiagnosAwalFragment();
                            break;

                        case R.id.navigation_profil:
                            fragment = new profile();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container,
                            fragment).commit();
                    return true;
                }
            };

 }