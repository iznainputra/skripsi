package com.example.putrabuwana.diagnosawal.User;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;

public class profile extends Fragment {

    TextView tv_no;
    SharedPreferences sharedpreferences;

    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";
    Button btn_logout;
    String no,nama;
    public static final String my_shared_preferences = "my_shared_preferences";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_profile, container, false);

        tv_no = (TextView) v.findViewById(R.id.tv_no);
        btn_logout = (Button) v.findViewById(R.id.btn_logout);

        sharedpreferences = getActivity().getSharedPreferences(profile.my_shared_preferences, Context.MODE_PRIVATE);
        no =sharedpreferences.getString(TAG_NO,null);
        nama =sharedpreferences.getString(TAG_USERNAME,null);


        tv_no.setText(no);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putBoolean(MainActivity.session_status, false);
                editor.putString(TAG_NO, null);
                editor.putString(TAG_USERNAME, null);
                editor.commit();

                Intent intent = new Intent(getContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return v;
    }

}
