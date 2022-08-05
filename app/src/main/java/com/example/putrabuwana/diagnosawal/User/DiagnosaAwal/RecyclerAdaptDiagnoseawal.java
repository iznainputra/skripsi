package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.R;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;

public class RecyclerAdaptDiagnoseawal extends RecyclerView.Adapter<RecyclerAdaptDiagnoseawal.MyViewHolder> {

//    Context mContext;
    SmileRating smileRating;
    private ArrayList<DataDiagnoseawal> mData;


    public RecyclerAdaptDiagnoseawal(ArrayList<DataDiagnoseawal> mData){
        this.mData = mData;
//        this.mContext = mContext;
    }


    @Override
    public RecyclerAdaptDiagnoseawal.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnoseawal, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_id.setText(String.valueOf(mData.get(position).getId()));
        holder.tv_deskripsi.setText(mData.get(position).getDeskripsi());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private TextView tv_deskripsi;
        private SmileRating smileRating;

        public MyViewHolder(final View ItemView){
            super(ItemView);
            tv_id = (TextView)itemView.findViewById(R.id.id);
            tv_deskripsi = (TextView)itemView.findViewById(R.id.deskripsi);
            smileRating =(SmileRating)ItemView.findViewById(R.id.smile_rating);
            smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener(){
                @Override
                public void onSmileySelected ( @BaseRating.Smiley int smiley, boolean reselected){

                    switch (smiley) {
                        case SmileRating.TERRIBLE:
                            Toast.makeText(smileRating.getContext(), "SELALU MENGALAMI", Toast.LENGTH_SHORT).show();
                            break;
                        case SmileRating.BAD:
                            Toast.makeText(smileRating.getContext(), "SERING MENGALAMI", Toast.LENGTH_SHORT).show();
                            break;
                        case SmileRating.OKAY:
                            Toast.makeText(smileRating.getContext(), "TERKADANG MENGALAMI", Toast.LENGTH_SHORT).show();
                            break;
                        case SmileRating.GOOD:
                            Toast.makeText(smileRating.getContext(), "JARANG MENGALAMI", Toast.LENGTH_SHORT).show();
                            break;
                        case SmileRating.GREAT:
                            Toast.makeText(smileRating.getContext(), "TIDAK PERNAH", Toast.LENGTH_SHORT).show();
                            break;

                    }
                }
            });
        }
    }
}
