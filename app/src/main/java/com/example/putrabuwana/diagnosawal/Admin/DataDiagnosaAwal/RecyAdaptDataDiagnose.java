package com.example.putrabuwana.diagnosawal.Admin.DataDiagnosaAwal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.DetailDatauser;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;

import java.util.ArrayList;

public class RecyAdaptDataDiagnose extends RecyclerView.Adapter<RecyAdaptDataDiagnose.MyViewHolder> {
        private Context mContext;
        private ArrayList<DataDiagnoseawal> mData;
        View view;


    public RecyAdaptDataDiagnose(Context mContext, ArrayList<DataDiagnoseawal> mData){
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public RecyAdaptDataDiagnose.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_admindiagawal, parent, false);
        MyViewHolder holder = new RecyAdaptDataDiagnose.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(mData.get(position).getId()));
        holder.tv_deskripsi.setText(mData.get(position).getDeskripsi());
        holder.carddiagitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailDataDiagnos.class);
                i.putExtra("id", holder.tv_id.getText());
                i.putExtra("deskripsi", holder.tv_deskripsi.getText());
                mContext.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_id;
        private TextView tv_deskripsi;
        private CardView carddiagitem;

       public MyViewHolder(View ItemView){
           super(ItemView);
           tv_id = (TextView)ItemView.findViewById(R.id.id);
           tv_deskripsi = (TextView)ItemView.findViewById(R.id.deskripsi);
           carddiagitem = (CardView) itemView.findViewById(R.id.carddiag_item);
       }
    }


}
