package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.putrabuwana.diagnosawal.Admin.DataUser.ApiDatauser;
import com.example.putrabuwana.diagnosawal.R;
import com.example.putrabuwana.diagnosawal.User.HomeFragment;
import com.example.putrabuwana.diagnosawal.User.Menu;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class MulaiDiagnose extends AppCompatActivity implements View.OnClickListener {
        TextView id;
        TextView deskripsi , kalimatdiag, nama, tl, saran, kategori, bbdn, txtnotgood, txtsmile, txthappy;
        Button btn_previous, btn_ok;
        Button btn_next, btn_fin, btn_last;
        SmileRating smileRating;
        ImageView happy, smile, notgood;

        private ArrayList<DataDiagnoseawal> DataDA;
        ApiDatatanya apiInterface;
        int no = 1;
        String statdepr;
        int zmax = 0;
        int zmin = 0;
        String rumustdepr1;
        String rumustdepr2;
        String rumusbb1;
        String rumusbb2;
        double Dottl;
        double Dobb;
        double fuzzy_tdepr1 = 0.0;
        double fuzzy_tdepr2 = 0.0;
        double fuzzy_bb1 = 0.0;
        double fuzzy_bb2 = 0.0;
        double pred_max = 0.0;
        double zi;
        ArrayList newdata = new ArrayList();
        TextView t_no, nm, bb;
        String nouser, nmatampil, bbe, bbtampil, nilai, klmtampil, srntampil, ktgrtampil;
        SharedPreferences sharedpreferences;
        public static final String TAG_NO = "no";
        public static final String TAG_NAMA = "nama";
        public static final String TAG_BERATBADAN = "berat_badan";
        public static final String my_shared_preferences = "my_shared_preferences";
        public static final String URL = "http://localhost/diagnosawal/index.php/api/RestAPI/datascreen/";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mulaidiagnose);
        id = (TextView)findViewById(R.id.id);
        deskripsi = (TextView)findViewById(R.id.deskripsi);
        btn_next = (Button) findViewById(R.id.btn_next);
        btn_last = (Button) findViewById(R.id.btn_last);
        btn_previous =(Button) findViewById(R.id.btn_previous);
        btn_fin =(Button) findViewById(R.id.btn_fin);
        smileRating =(SmileRating)findViewById(R.id.smile_rating);
        smileRating.setNameForSmile(BaseRating.GREAT,"Tidak");
        smileRating.setNameForSmile(BaseRating.GOOD,"Jarang");
        smileRating.setNameForSmile(BaseRating.OKAY,"Terkadang");
        smileRating.setNameForSmile(BaseRating.BAD,"Sering");
        smileRating.setNameForSmile(BaseRating.TERRIBLE,"Selalu");
        happy = findViewById(R.id.happy);
        smile = findViewById(R.id.smile);
        notgood = findViewById(R.id.notgood);
        txthappy = findViewById(R.id.txthappy);
        txtsmile = findViewById(R.id.txtsmile);
        txtnotgood = findViewById(R.id.txtnotgood);


        t_no = (TextView) findViewById(R.id.t_no);
        nm = (TextView) findViewById(R.id.nm);
        bb = (TextView) findViewById(R.id.bb);



        sharedpreferences = getSharedPreferences(MulaiDiagnose.my_shared_preferences, Context.MODE_PRIVATE);
        nouser = sharedpreferences.getString(TAG_NO,null);
        nmatampil = sharedpreferences.getString(TAG_NAMA,null);
        bbe = sharedpreferences.getString(TAG_BERATBADAN,null);
        t_no.setText(nouser);
        nm.setText(nmatampil);
        bb.setText(bbe);



        findViewById(R.id.btn_next).setOnClickListener(this);
        findViewById(R.id.btn_previous).setOnClickListener(this);
        findViewById(R.id.btn_fin).setOnClickListener(this);
        findViewById(R.id.btn_last).setOnClickListener(this);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setEnabled(true);
                btn_last.setEnabled(true);
                happy.setImageResource(R.drawable.ic_111clremoticonsmile);
                txthappy.setTextColor(Color.BLACK);
                txtsmile.setTextColor(Color.parseColor("#b8b9be"));
                txtnotgood.setTextColor(Color.parseColor("#b8b9be"));
                smile.setImageResource(R.drawable.ic_222emoticonsmiley);
                notgood.setImageResource(R.drawable.ic_333emoticonnotgood);
                nilai = "0";
            }
        });

        smile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setEnabled(true);
                btn_last.setEnabled(true);
                happy.setImageResource(R.drawable.ic_111emoticonsmile);
                smile.setImageResource(R.drawable.ic_222clremoticonsmiley);
                txtsmile.setTextColor(Color.BLACK);
                txthappy.setTextColor(Color.parseColor("#b8b9be"));
                txtnotgood.setTextColor(Color.parseColor("#b8b9be"));
                notgood.setImageResource(R.drawable.ic_333emoticonnotgood);
                nilai = "1";
            }
        });

        notgood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_next.setEnabled(true);
                btn_last.setEnabled(true);
                happy.setImageResource(R.drawable.ic_111emoticonsmile);
                smile.setImageResource(R.drawable.ic_222emoticonsmiley);
                notgood.setImageResource(R.drawable.ic_333clremoticonnotgood);
                txtnotgood.setTextColor(Color.BLACK);
                txtsmile.setTextColor(Color.parseColor("#b8b9be"));
                txthappy.setTextColor(Color.parseColor("#b8b9be"));
                nilai = "2";
            }
        });

        smileRating.setOnSmileySelectionListener(new SmileRating.OnSmileySelectionListener(){
            @Override
            public void onSmileySelected (@BaseRating.Smiley int smiley, boolean reselected){


                switch (smiley) {
                    case SmileRating.TERRIBLE:
                        btn_next.setEnabled(true);
                        nilai = "4";
//                        Toast.makeText(smileRating.getContext(), "Pertanyaan no : "+ id +"SELALU MENGALAMI"+" Nilai : "+nilai, Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.BAD:
                        btn_next.setEnabled(true);
                        nilai = "3";
//                        Toast.makeText(smileRating.getContext(), "Pertanyaan no : "+ id +"SERING MENGALAMI"+" Nilai : "+nilai, Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.OKAY:
                        btn_next.setEnabled(true);
                        nilai = "2";
//                        Toast.makeText(smileRating.getContext(), "Pertanyaan no : "+ id +"TERKADANG MENGALAMI"+" Nilai : "+nilai, Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GOOD:
                        btn_next.setEnabled(true);
                        nilai = "1";
//                        Toast.makeText(smileRating.getContext(), "Pertanyaan no : "+ id +"JARANG MENGALAMI"+" Nilai : "+nilai, Toast.LENGTH_SHORT).show();
                        break;
                    case SmileRating.GREAT:
                        btn_next.setEnabled(true);
                        nilai = "0";
//                        Toast.makeText(smileRating.getContext(), "Pertanyaan no : "+ id +"TIDAK PERNAH"+" Nilai : "+nilai, Toast.LENGTH_SHORT).show();
                        break;

                }
            }


        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://temporaltime.com/diagnosawal/index.php/api/RestAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiDatatanya.class);
        LoadJSON();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_next:
                Next();
                break;
            case R.id.btn_previous:
                if (no == 1){
                    Toast.makeText(btn_next.getContext(), "START", Toast.LENGTH_SHORT).show();
                    btn_previous.setVisibility(INVISIBLE);
                    btn_next.setVisibility(VISIBLE);
                    btn_fin.setVisibility(INVISIBLE);
                } else {
                    Previous();
                }
                break;
            case R.id.btn_last:
                    if(no == 17) {
                        Toast.makeText(btn_next.getContext(), "FINISH", Toast.LENGTH_SHORT).show();
                        newdata.add(nilai);
                        btn_last.setVisibility(INVISIBLE);
                        btn_fin.setVisibility(VISIBLE);
                    } else {
                        Next();
                    }
                break;
            case R.id.btn_fin:
                Finish();
//                Collections.sort(newdata);
                break;
        }
    }
    private void LoadJSON() {

        Call<ArrayList<DataDiagnoseawal>> call = apiInterface.getDetail(
                String.valueOf(no)
        );
        call.enqueue(new Callback<ArrayList<DataDiagnoseawal>>() {

            @Override
            public void onResponse(Call <ArrayList<DataDiagnoseawal>> call, Response<ArrayList<DataDiagnoseawal>> response) {
                if (response.isSuccessful()) {
                    ArrayList<DataDiagnoseawal> list = response.body();
                    id.setText(list.get(0).getId());
                    deskripsi.setText(list.get(0).getDeskripsi());
//                    String idques = id.getText().toString();
//                    String deskques = deskripsi.getText().toString();
//                    Toast.makeText(btn_next.getContext(), "Pertanyaan : " + idques + ". " +deskques, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call <ArrayList<DataDiagnoseawal>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }


        });

    }

    private void Next() {
        btn_next.setEnabled(false);
        if (no >= 8){
        smileRating.setVisibility(INVISIBLE);
        happy.setVisibility(VISIBLE);
        txthappy.setVisibility(VISIBLE);
        smile.setVisibility(VISIBLE);
        txtsmile.setVisibility(VISIBLE);
        notgood.setVisibility(VISIBLE);
        txtnotgood.setVisibility(VISIBLE);
    }
        if (no == 16){
            btn_next.setVisibility(INVISIBLE);
            smileRating.setVisibility(INVISIBLE);
            happy.setVisibility(VISIBLE);
            txthappy.setVisibility(VISIBLE);
            smile.setVisibility(VISIBLE);
            txtsmile.setVisibility(VISIBLE);
            notgood.setVisibility(VISIBLE);
            txtnotgood.setVisibility(VISIBLE);
            btn_last.setVisibility(VISIBLE);
//                    btn_fin.setVisibility(v.VISIBLE);
        } else {
//                    Toast.makeText(btn_next.getContext(), "urutan : "+no, Toast.LENGTH_SHORT).show();
            btn_previous.setVisibility(VISIBLE);
        }
//        String noser = t_no.getText().toString();
//        Toast.makeText(btn_next.getContext(), "User : "+noser, Toast.LENGTH_SHORT).show();
            Call<ArrayList<DataDiagnoseawal>> call = apiInterface.getDetail(

                    String.valueOf(no = no + 1)


            );
        call.enqueue(new Callback<ArrayList<DataDiagnoseawal>>() {

            @Override
            public void onResponse(Call <ArrayList<DataDiagnoseawal>> call, Response<ArrayList<DataDiagnoseawal>> response) {
                if (response.isSuccessful()) {
                    ArrayList<DataDiagnoseawal> list = response.body();
                    id.setText(list.get(0).getId());
                    deskripsi.setText(list.get(0).getDeskripsi());
//                    String idques = id.getText().toString();
//                    String deskques = deskripsi.getText().toString();
                    newdata.add(nilai);
                    smileRating.setSelectedSmile(BaseRating.NONE);
                    happy.setImageResource(R.drawable.ic_111emoticonsmile);
                    smile.setImageResource(R.drawable.ic_222emoticonsmiley);
                    notgood.setImageResource(R.drawable.ic_333emoticonnotgood);
                    txtsmile.setTextColor(Color.parseColor("#b8b9be"));
                    txthappy.setTextColor(Color.parseColor("#b8b9be"));
                    txtnotgood.setTextColor(Color.parseColor("#b8b9be"));
                    Toast.makeText(btn_next.getContext(), " Next Question ", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call <ArrayList<DataDiagnoseawal>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }


        });

    }

    private void Previous() {
        btn_next.setEnabled(false);
        if (no <= 9){
            smileRating.setVisibility(VISIBLE);
            happy.setVisibility(INVISIBLE);
            txthappy.setVisibility(INVISIBLE);
            smile.setVisibility(INVISIBLE);
            txtsmile.setVisibility(INVISIBLE);
            notgood.setVisibility(INVISIBLE);
            txtnotgood.setVisibility(INVISIBLE);
        }
        if(no <= 17){
            btn_last.setVisibility(INVISIBLE);
            btn_next.setVisibility(VISIBLE);
            btn_fin.setVisibility(INVISIBLE);
        } else {
//                    Toast.makeText(btn_next.getContext(), "urutan : "+no, Toast.LENGTH_SHORT).show();
            btn_next.setVisibility(VISIBLE);

        }
        Call<ArrayList<DataDiagnoseawal>> call = apiInterface.getDetail(

                String.valueOf(no = no - 1)

        );
        call.enqueue(new Callback<ArrayList<DataDiagnoseawal>>() {

            @Override
            public void onResponse(Call <ArrayList<DataDiagnoseawal>> call, Response<ArrayList<DataDiagnoseawal>> response) {
                if (response.isSuccessful()) {
                    ArrayList<DataDiagnoseawal> list = response.body();
                    id.setText(list.get(0).getId());
                    deskripsi.setText(list.get(0).getDeskripsi());
                    newdata.remove(newdata.size() - 1);
                    smileRating.setSelectedSmile(BaseRating.NONE);
                    happy.setImageResource(R.drawable.ic_111emoticonsmile);
                    smile.setImageResource(R.drawable.ic_222emoticonsmiley);
                    notgood.setImageResource(R.drawable.ic_333emoticonnotgood);
                    txtsmile.setTextColor(Color.parseColor("#b8b9be"));
                    txthappy.setTextColor(Color.parseColor("#b8b9be"));
                    txtnotgood.setTextColor(Color.parseColor("#b8b9be"));
                    Toast.makeText(btn_next.getContext(), " Previous Question ", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onFailure(Call <ArrayList<DataDiagnoseawal>> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }


        });

    }

    private void Finish() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MulaiDiagnose.this);
        final View mView = getLayoutInflater().inflate(R.layout.alertdialog_diagnos,null);
        mBuilder.setView(mView);
        final String no = t_no.getText().toString().trim();
        String ss1 = newdata.get(0).toString();
        String ss2 = newdata.get(1).toString();
        String ss3 = newdata.get(2).toString();
        String ss4 = newdata.get(3).toString();
        String ss5 = newdata.get(4).toString();
        String ss6 = newdata.get(5).toString();
        String ss7 = newdata.get(6).toString();
        String ss8 = newdata.get(7).toString();
        String ss9 = newdata.get(8).toString();
        String ss10 = newdata.get(9).toString();
        String ss11 = newdata.get(10).toString();
        String ss12 = newdata.get(11).toString();
        String ss13 = newdata.get(12).toString();
        String ss14 = newdata.get(13).toString();
        String ss15 = newdata.get(14).toString();
        String ss16 = newdata.get(15).toString();
        String ss17 = newdata.get(16).toString();
        Integer s1 = Integer.parseInt(ss1);
        Integer s2 = Integer.parseInt(ss2);
        Integer s3 = Integer.parseInt(ss3);
        Integer s4 = Integer.parseInt(ss4);
        Integer s5 = Integer.parseInt(ss5);
        Integer s6 = Integer.parseInt(ss6);
        Integer s7 = Integer.parseInt(ss7);
        Integer s8 = Integer.parseInt(ss8);
        Integer s9 = Integer.parseInt(ss9);
        Integer s10 = Integer.parseInt(ss10);
        Integer s11 = Integer.parseInt(ss11);
        Integer s12 = Integer.parseInt(ss12);
        Integer s13 = Integer.parseInt(ss13);
        Integer s14 = Integer.parseInt(ss14);
        Integer s15 = Integer.parseInt(ss15);
        Integer s16 = Integer.parseInt(ss16);
        Integer s17 = Integer.parseInt(ss17);
        final Integer beratb = Integer.parseInt(bbe);
        final Integer total_gejala = s1 + s2 + s3 + s4 + s5 + s6 + s7 + s8 + s9 + s10 + s11 + s12 + s13 + s14 + s15 + s16 + s17;
        System.out.println("\nPanjang deret setelah ditambah elemen: "+newdata.size());
        for (int i = 0; i < newdata.size(); i++){
            System.out.println("elemen ke - " + i + " : " + newdata.get(i));
        }

        kalimatdiag = (TextView) mView.findViewById(R.id.kalimatdiag);
        tl = (TextView) mView.findViewById(R.id.tl);
        saran = (TextView) mView.findViewById(R.id.saran);
        kategori = (TextView) mView.findViewById(R.id.kategori);
        bbdn = (TextView) mView.findViewById(R.id.bbdn);
        btn_ok = (Button) mView.findViewById(R.id.btn_ok);
        //////////////////////BERAT BADAN KURUS//////////////////////////


        Dottl = total_gejala;
        Dobb = beratb;
        if(total_gejala <= 7 && beratb < 51){
            statdepr = "Sangat Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton menghasilkan status : SANGAT BAIK";
            ktgrtampil = "Normal";
            bbtampil = "Kurus";
            srntampil = "Tetap semangat menggapai impian anda, Jangan menyerah. Selalu berdoa dan berusaha.";
            ////////////himpunan fuzzy depresi Normal///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala <= 7 && total_gejala <= 13){
                rumustdepr1 = "(x - 7) / 6";
                rumustdepr2 = "(13 - x) / 6";
                fuzzy_tdepr1 = (Dottl - 7)/6;
                fuzzy_tdepr2 = (13 - Dottl)/6;
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }
            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Kurus///////////
            if(beratb <= 40){
                fuzzy_bb1 = 0;
            } else if (beratb >= 40 && beratb <= 50){
                rumusbb1 = "(50 - x) / 10";
                rumusbb2 = "(x - 45) / 6";
                fuzzy_bb1 = (50 - Dobb)/10;
                fuzzy_bb2 = (Dobb - 45)/6;
            } else if (beratb >= 50){
                fuzzy_bb1 = 1;
            }
            /////////////////////////////////////////
        } else if(total_gejala <= 13  && total_gejala >= 8 && beratb < 51){
            statdepr = "Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : BAIK";
            ktgrtampil = "Ringan";
            bbtampil = "Kurus";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda. dan juga istirahat yang teratur. dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Ringan///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 7 && total_gejala <= 13){
                rumustdepr1 = "(x - 7) / 6";
                rumustdepr2 = "(13 - x) / 6";
                fuzzy_tdepr1 = (Dottl - 7)/6;
                fuzzy_tdepr2 = (13 - Dottl)/6;
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }
            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Kurus///////////
            if(beratb >= 50){
                fuzzy_bb1 = 1;
            } else if (beratb > 40 && beratb <= 50){
                rumusbb1 = "(50 - x) / 10";
                rumusbb2 = "(x - 45) / 6";
                fuzzy_bb1 = (50 - Dobb)/10;
                fuzzy_bb2 = (Dobb - 45)/6;
            } else if (beratb <= 40){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 18  && total_gejala >= 14 && beratb < 51){
            statdepr = "Sedang";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SEDANG";
            ktgrtampil = "Sedang";
            bbtampil = "Kurus";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin dan jangan lupa istirahat yang teratur dan makan yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Sedang///////////
            if (total_gejala <= 10 && total_gejala <= 20){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 10 && total_gejala >= 14){
                fuzzy_tdepr1 = (Dottl - 10)/(14-10);
            }else if(total_gejala >= 14 && total_gejala <= 18){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 18 && total_gejala <= 20){
                fuzzy_tdepr1 = (20-Dottl)/(20-18);
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Kurus///////////
            if(beratb <= 50){
                fuzzy_bb1 = 1;
            } else if (beratb >= 40 && beratb <= 50){
                fuzzy_bb1 = (50 - Dobb)/(50-40);
                fuzzy_bb2 = (Dobb - 45)/(51-45);
            } else if (beratb >= 40){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 22  && total_gejala >= 19 && beratb < 51){
            statdepr = "Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : BURUK";
            ktgrtampil = "Berat";
            bbtampil = "Kurus";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin, jangan lupa istirahat yang teratur, semua orang disekitar anda mencintai anda. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat ";
            ////////////himpunan fuzzy depresi Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (Dottl - 19)/(22-19);
                fuzzy_tdepr2 = (22 - Dottl)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Kurus///////////
            if(beratb <= 50){
                fuzzy_bb1 = 1;
            } else if (beratb >= 40 && beratb <= 50){
                fuzzy_bb1 = (50 - Dobb)/(50-40);
                fuzzy_bb2 = (Dobb - 45)/(51-45);
            } else if (beratb >= 40){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala >= 23 && beratb < 51){
            statdepr = "Sangat Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SANGAT BURUK";
            ktgrtampil = "Sangat Berat";
            bbtampil = "Kurus";
            srntampil = "Orang-orang di sekitar anda peduli dengan anda cobalah menghubungi orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin, jangan lupa tidur dan makan yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat";
            ////////////himpunan fuzzy depresi Sangat Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (22 - Dottl)/(22-19);
                fuzzy_tdepr2 = (Dottl - 19)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Kurus///////////
            if(beratb <= 50){
                fuzzy_bb1 = 1;
            } else if (beratb >= 40 && beratb <= 50){
                fuzzy_bb1 = (50 - Dobb)/(50-40);
                fuzzy_bb2 = (Dobb - 45)/(51-45);
            } else if (beratb >= 40){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////


        }//////////////////////BERAT BADAN IDEAL//////////////////////////
        else if(total_gejala <= 7 && beratb >= 51 && beratb <= 64){
            statdepr = "Sangat Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SANGAT BAIK";
            ktgrtampil = "Normal";
            bbtampil = "Ideal";
            srntampil = "Tetap semangat menggapai impian anda, Jangan menyerah. Selalu berdoa dan berusaha.";
            ////////////himpunan fuzzy depresi Normal///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 7 && total_gejala <= 13){
                fuzzy_tdepr1 = (Dottl - 7)/(13-7);
                fuzzy_tdepr2 = (13 - Dottl)/(13-7);
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }
            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Ideal///////////
            if(beratb <= 51 || beratb <= 64){
                fuzzy_bb1 = 1;
            } else if (beratb >= 45 && beratb <= 51){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb >= 64 && beratb <= 70){
                fuzzy_bb1 = (70 - Dobb)/(70-64);
            } else if (beratb >= 45 || beratb <= 70){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        } else if(total_gejala <= 13  && total_gejala >= 8 && beratb >= 51 && beratb <= 64){
            statdepr = "Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : BAIK";
            ktgrtampil = "Ringan";
            bbtampil = "Ideal";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda. dan juga istirahat yang teratur. dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Ringan///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 7 && total_gejala <= 13){
                fuzzy_tdepr1 = (Dottl - 7)/(13-7);
                fuzzy_tdepr2 = (13 - Dottl)/(13-7);
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }

            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Ideal///////////
            if(beratb <= 51 || beratb <= 64){
                fuzzy_bb1 = 1;
            } else if (beratb >= 45 && beratb <= 51){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb >= 64 && beratb <= 70){
                fuzzy_bb1 = (70 - Dobb)/(70-64);
            } else if (beratb >= 45 || beratb <= 70){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 18  && total_gejala >= 14 && beratb >= 51 && beratb <= 64){
            statdepr = "Sedang";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : Sedang";
            ktgrtampil = "Sedang";
            bbtampil = "Ideal";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin dan jangan lupa istirahat yang teratur dan makan yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Sedang///////////
            if (total_gejala <= 10 && total_gejala <= 20){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 10 && total_gejala >= 14){
                fuzzy_tdepr1 = (Dottl - 10)/(14-10);
            }else if(total_gejala >= 14 && total_gejala <= 18){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 18 && total_gejala <= 20){
                fuzzy_tdepr1 = (20-Dottl)/(20-18);
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Ideal///////////
            if(beratb <= 51 || beratb <= 64){
                fuzzy_bb1 = 1;
            } else if (beratb >= 45 && beratb <= 51){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb >= 64 && beratb <= 70){
                fuzzy_bb1 = (70 - Dobb)/(70-64);
            } else if (beratb >= 45 || beratb <= 70){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 22  && total_gejala >= 19 && beratb >= 51 && beratb <= 64){
            statdepr = "Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : BURUK";
            ktgrtampil = "Berat";
            bbtampil = "Ideal";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada teman/orang terdekat anda, lakukan olahraga rutin, jangan lupa istirahat yang teratur, semua orang disekitar anda mencintai anda. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat";
            ////////////himpunan fuzzy depresi Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (Dottl - 19)/(22-19);
                fuzzy_tdepr2 = (22 - Dottl)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Ideal///////////
            if(beratb <= 51 || beratb <= 64){
                fuzzy_bb1 = 1;
            } else if (beratb >= 45 && beratb <= 51){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb >= 64 && beratb <= 70){
                fuzzy_bb1 = (70 - Dobb)/(70-64);
            } else if (beratb >= 45 || beratb <= 70){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala >= 23 && beratb >= 51 && beratb <= 64){
            statdepr = "Sangat Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SANGAT BURUK";
            ktgrtampil = "Sangat Berat";
            bbtampil = "Ideal";
            srntampil = "Orang-orang di sekitar anda peduli dengan anda cobalah menghubungi orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin, jangan lupa tidur dan makan yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat";
            ////////////himpunan fuzzy depresi Sangat Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (22 - Dottl)/(22-19);
                fuzzy_tdepr2 = (Dottl - 19)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Ideal///////////
            if(beratb <= 51 || beratb <= 64){
                fuzzy_bb1 = 1;
            } else if (beratb >= 45 && beratb <= 51){
                fuzzy_bb2 = (Dobb - 45)/(51-45);
            } else if (beratb >= 64 && beratb <= 70){
                fuzzy_bb1 = (70 - Dobb)/(70-64);
            } else if (beratb >= 45 || beratb <= 70){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }//////////////////////BERAT BADAN BERAT//////////////////////////
        else if(total_gejala <= 7 && beratb >= 65){
            statdepr = "Sangat Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SANGAT BAIK";
            ktgrtampil = "Normal";
            bbtampil = "Berat";
            srntampil = "Tetap semangat menggapai impian anda, Jangan menyerah. Selalu berdoa dan berusaha.";
            ////////////himpunan fuzzy depresi Normal///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 7 && total_gejala <= 13){
                fuzzy_tdepr1 = (Dottl - 7)/(13-7);
                fuzzy_tdepr2 = (13 - Dottl)/(13-7);
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }
            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Berat///////////
            if(beratb <= 90){
                fuzzy_bb1 = 1;
            } else if (beratb >= 65 && beratb <= 90){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb <= 65){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        } else if(total_gejala <= 13  && total_gejala >= 8 && beratb >= 65){
            statdepr = "Baik";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : Baik";
            ktgrtampil = "Ringan";
            bbtampil = "Berat";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda. dan juga istirahat yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Ringan///////////
            if(total_gejala <= 7){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 7 && total_gejala <= 13){
                fuzzy_tdepr1 = (Dottl - 7)/(13-7);
                fuzzy_tdepr2 = (13 - Dottl)/(13-7);
            } else if (total_gejala >= 13){
                fuzzy_tdepr1 = 0;
            }
            /////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Berat///////////
            if(beratb <= 90){
                fuzzy_bb1 = 1;
            } else if (beratb >= 65 && beratb <= 90){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb <= 65){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 18  && total_gejala >= 14 && beratb >= 65){
            statdepr = "Sedang";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : Sedang";
            ktgrtampil = "Sedang";
            bbtampil = "Berat";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin dan jangan lupa istirahat yang teratur dan makan yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa";
            ////////////himpunan fuzzy depresi Sedang///////////
            if (total_gejala <= 10 && total_gejala <= 20){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 10 && total_gejala >= 14){
                fuzzy_tdepr1 = (Dottl - 10)/(14-10);
            }else if(total_gejala >= 14 && total_gejala <= 18){
                fuzzy_tdepr1 = 1;
            } else if (total_gejala >= 18 && total_gejala <= 20){
                fuzzy_tdepr1 = (20-Dottl)/(20-8);
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Berat///////////
            if(beratb <= 90){
                fuzzy_bb1 = 1;
            } else if (beratb >= 65 && beratb <= 90){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb <= 65){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala <= 22  && total_gejala >= 19 && beratb >= 65){
            statdepr = "Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : BURUK";
            ktgrtampil = "Berat";
            bbtampil = "Berat";
            srntampil = "Cari teman/orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin, jangan lupa istirahat yang teratur, semua orang disekitar anda mencintai anda. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat";
            ////////////himpunan fuzzy depresi Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (Dottl - 19)/(22-19);
                fuzzy_tdepr2 = (19 - Dottl)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Berat///////////
            if(beratb <= 90){
                fuzzy_bb1 = 1;
            } else if (beratb >= 65 && beratb <= 90){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb <= 65){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }else if(total_gejala >= 23 && beratb >= 65){
            statdepr = "Sangat Buruk";
            klmtampil = "Anda telah menyelesaikan kuesioner gejala awal depresi dengan Fuzzy Berat Badan dan Skala Depresi Max Hamilton  menghasilkan status : SANGAT BURUK";
            ktgrtampil = "Sangat Berat";
            bbtampil = "Berat";
            srntampil = "Orang-orang di sekitar anda peduli dengan anda cobalah menghubungi orang terdekat anda curahkan isi hati anda kepada orang terdekat anda, lakukan olahraga rutin, jangan lupa tidur dan makan  yang teratur. serta dekatkan diri kepada Tuhan Yang Maha Esa. Apabila anda membutuhkan seseorang yang ahli anda dapat menghubungi Psikologi atau Psikiater terdekat";
            ////////////himpunan fuzzy depresi Sangat Berat///////////
            if (total_gejala <= 19){
                fuzzy_tdepr1 = 0;
            } else if(total_gejala >= 19 && total_gejala >= 22){
                fuzzy_tdepr1 = (19 - Dottl)/(22-19);
                fuzzy_tdepr2 = (Dottl - 19)/(22-19);
            }else if(total_gejala >= 22){
                fuzzy_tdepr1 = 1;
            }
            /////////////////////////////////////////////
            ////////////himpunan fuzzy berat badan Berat///////////
            if(beratb <= 90){
                fuzzy_bb1 = 1;
            } else if (beratb >= 65 && beratb <= 90){
                fuzzy_bb1 = (Dobb - 45)/(51-45);
            } else if (beratb <= 65){
                fuzzy_bb1 = 0;
            }
            /////////////////////////////////////////
        }
        System.out.println("\n Rumus tderp1 " + rumustdepr1);
        System.out.println("\n Fuzzy tderp 1 " + fuzzy_tdepr1);
        System.out.println("\n Rumus tderp2 " + rumustdepr2);
        System.out.println("\n Fuzzy tderp 2 " + fuzzy_tdepr2);
        System.out.println("\n Rumus bb1 " +beratb +" "+ rumusbb1);
        System.out.println("\n Fuzzy bb 1 " + fuzzy_bb1);
        System.out.println("\n Rumus bb2 " + rumusbb2);
        System.out.println("\n Fuzzy bb 2 " + fuzzy_bb2);
        if(fuzzy_tdepr1 > fuzzy_tdepr2){
            pred_max = fuzzy_tdepr1;
        } else if(fuzzy_tdepr2 > fuzzy_tdepr1){
            pred_max = fuzzy_tdepr2;
        }
        if(statdepr == "Sangat Baik"){
            zmax = 10;
            zmin = 0;
        }else if(statdepr == "Baik"){
            zmax = 20;
            zmin = 11;
        }else if(statdepr == "Sedang"){
            zmax = 30;
            zmin = 21;
        }else if(statdepr == "Buruk"){
            zmax = 40;
            zmin = 31;
        }else if(statdepr == "Sangat Buruk"){
            zmax = 50;
            zmin = 41;
        }
        zi = zmax - pred_max * (zmax - zmin);


        System.out.println("\n Predikat 1 " + pred_max);
        System.out.println("\n Persamaan Z1 " + zi);
        kalimatdiag.setText(klmtampil);
        tl.setText(String.valueOf(total_gejala));
        kategori.setText(ktgrtampil);
        saran.setText(srntampil);
        bbdn.setText(bbtampil);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(), Menu.class));


            }
        });

//        Toast.makeText(getApplicationContext(), " Array : "+ newdata +" , data1 :"+ s1 +" , data2 :"+ s2 +" , data3 :"+ s3 +" , data4 :"+ s4 +" , data5 :"+ s5 +" , data6 :"+ s6 +" , data7 :"+ s7 +" , data8 :"+ s8 +" , data9 :"+ s9 +" , data10 :"+ s10 +" , data11 :"+ s11 +" , data12 :"+ s12 +" , data13 :"+ s13 +" , data14 :"+ s14 +" , data15 :"+ s15 +" , data16 :"+ s16 +" , data17 :"+ s17 +" , TOTAL : " + total_gejala, Toast.LENGTH_SHORT).show();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiDataScreenUser api = retrofit.create(ApiDataScreenUser.class);
        Call<ResponseBody> call = api.update(no, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13, s14, s15, s16, s17, total_gejala);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(getApplicationContext(), "TEST COMPLETE!", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "FAILED! ", Toast.LENGTH_SHORT).show();

            }
        });
    }




}
