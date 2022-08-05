package com.example.putrabuwana.diagnosawal.Admin;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.profile;

public class profileAdmin extends Fragment {
    TextView tv_no,tv_username;
    SharedPreferences sharedpreferencesadmin;

    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";
    Button btn_logout;
    String no,username;
    public static final String my_shared_preferencesadmin = "my_shared_preferencesadmin";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile_admin, container, false);

        tv_no = (TextView) v.findViewById(R.id.tv_no);
        tv_username = (TextView) v.findViewById(R.id.tv_username);
        btn_logout = (Button) v.findViewById(R.id.btn_logout);

        sharedpreferencesadmin = getActivity().getSharedPreferences(profileAdmin.my_shared_preferencesadmin, Context.MODE_PRIVATE);
        no =sharedpreferencesadmin.getString(TAG_NO,null);
        username =sharedpreferencesadmin.getString(TAG_USERNAME,null);


        tv_no.setText(no);
        tv_username.setText(username);

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferencesadmin.edit();
                editor.putBoolean(MainActivity.session_statusadmin, false);
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
