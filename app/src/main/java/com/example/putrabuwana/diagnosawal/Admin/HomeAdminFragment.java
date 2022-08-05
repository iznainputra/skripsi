package com.example.putrabuwana.diagnosawal.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.MainActivity;
import com.example.putrabuwana.diagnosawal.R;


public class HomeAdminFragment extends Fragment {

    SharedPreferences sharedpreferencesadmin;
    String no, username;
    public static final String TAG_NO = "no";
    public static final String TAG_USERNAME = "username";
    public static final String my_shared_preferencesadmin = "my_shared_preferencesadmin";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragadmin_home, container, false);
        sharedpreferencesadmin = getActivity().getSharedPreferences(HomeAdminFragment.my_shared_preferencesadmin, Context.MODE_PRIVATE);
        no =sharedpreferencesadmin.getString(TAG_NO,null);
        username =sharedpreferencesadmin.getString(TAG_USERNAME,null);

        return v;
    }
}
