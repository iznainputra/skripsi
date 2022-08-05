package com.example.putrabuwana.diagnosawal.Admin.DataUser;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.DataDiagnoseawal;
import com.example.putrabuwana.diagnosawal.User.DiagnosaAwal.RecyclerAdaptDiagnoseawal;

import java.util.ArrayList;

public class RecyclerAdaptDatauser extends RecyclerView.Adapter<RecyclerAdaptDatauser.MyViewHolder>   {
    private Context mContext;
    private ArrayList<Datauser> mDuser;
    View view;


    public RecyclerAdaptDatauser(Context mContext, ArrayList<Datauser> mDuser) {
        this.mContext = mContext;
        this.mDuser = mDuser;

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_datauser, parent, false);
        MyViewHolder holder = new RecyclerAdaptDatauser.MyViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return mDuser.size();
    }

    @Override
    public void onBindViewHolder(final RecyclerAdaptDatauser.MyViewHolder holder, int position) {
        Datauser datus = mDuser.get(position);
        holder.tv_no.setText(String.valueOf(mDuser.get(position).getNo()));
        holder.tv_nama.setText(mDuser.get(position).getNama());
        holder.tv_no_tlp.setText(mDuser.get(position).getNo_tlp());
        holder.tv_email.setText(String.valueOf(mDuser.get(position).getEmail()));
        holder.tv_tmp_lahir.setText(mDuser.get(position).getTmp_lahir());
        holder.tv_tgl_lahir.setText(String.valueOf(mDuser.get(position).getTgl_lahir()));
        holder.tv_alamat.setText(mDuser.get(position).getAlamat());
        holder.tv_berat_badan.setText(mDuser.get(position).getBerat_badan());
        holder.tv_username.setText(mDuser.get(position).getUsername());
        holder.carditem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailDatauser.class);
                i.putExtra("no", holder.tv_no.getText());
                i.putExtra("nama", holder.tv_nama.getText());
                i.putExtra("no_tlp", holder.tv_no_tlp.getText());
                i.putExtra("email", holder.tv_email.getText());
                i.putExtra("tmp_lahir", holder.tv_tmp_lahir.getText());
                i.putExtra("tgl_lahir", holder.tv_tgl_lahir.getText());
                i.putExtra("alamat", holder.tv_alamat.getText());
                i.putExtra("berat_badan", holder.tv_berat_badan.getText());
                i.putExtra("username", holder.tv_username.getText());
                mContext.startActivity(i);
            }
        });

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_no;
        private TextView tv_nama;
        private CardView carditem;
        private TextView tv_no_tlp;
        private TextView tv_email;
        private TextView tv_tmp_lahir;
        private TextView tv_tgl_lahir;
        private TextView tv_alamat;
        private TextView tv_berat_badan;
        private TextView tv_username;

        public MyViewHolder(View ItemView) {
            super(ItemView);
            tv_no = (TextView) itemView.findViewById(R.id.no);
            tv_nama = (TextView) itemView.findViewById(R.id.nama);
            tv_no_tlp = (TextView)itemView.findViewById(R.id.no_tlp);
            tv_email = (TextView)itemView.findViewById(R.id.email);
            tv_tmp_lahir = (TextView)itemView.findViewById(R.id.tmp_lahir);
            tv_tgl_lahir = (TextView)itemView.findViewById(R.id.tgl_lahir);
            tv_alamat = (TextView)itemView.findViewById(R.id.alamat);
            tv_berat_badan = (TextView)itemView.findViewById(R.id.berat_badan);
            tv_username = (TextView)itemView.findViewById(R.id.username);
            carditem = (CardView) itemView.findViewById(R.id.card_item);

        }
    }
}
