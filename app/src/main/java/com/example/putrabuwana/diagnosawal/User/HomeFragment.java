package com.example.putrabuwana.diagnosawal.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.R;

public class HomeFragment extends Fragment {
    Button cari;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_home, container, false);
        cari = (Button) v.findViewById(R.id.caripsikolog);

        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Cari Psikologi dan Psikiater sekitar", Toast.LENGTH_LONG).show();
                Intent in = new Intent(getActivity(),webvcari.class);
                startActivity(in);
            }
        });
        return v;
    }
}
