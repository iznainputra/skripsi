package com.example.putrabuwana.diagnosawal.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal.DataDiagnosAwalFragment;
import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.Admin.DataUser.DataUserFragment;
import com.example.putrabuwana.diagnosawal.User.HomeFragment;

public class MenuAdmin extends AppCompatActivity {

    SharedPreferences sharedpreferencesadmin;
    TextView txt_no, txt_username;
    String no, username;
    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityadmin_menu);
        txt_no = (TextView) findViewById(R.id.txt_no);
        txt_username = (TextView) findViewById(R.id.txt_username);


        sharedpreferencesadmin = getSharedPreferences(MainActivity.my_shared_preferencesadmin, Context.MODE_PRIVATE);

        no = getIntent().getStringExtra(TAG_NO);
        username = getIntent().getStringExtra(TAG_USERNAME);

        txt_no.setText("ID : " + no);
        txt_username.setText("USERNAME : " + username);
        BottomNavigationView navView = findViewById(R.id.bottom_navadmin_menu);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frag_containeradmin,
                new HomeFragment()).commit();
    }




    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            fragment = new HomeAdminFragment();
                            break;

                        case R.id.navigation_datauser:
                            fragment = new DataUserFragment();
                            break;

                        case R.id.navigation_datadiagnose:
                            fragment = new DataDiagnosAwalFragment();
                            break;

                        case R.id.navigation_profil:
                            fragment = new profileAdmin();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_containeradmin,
                            fragment).commit();
                    return true;
                }

            };
}
