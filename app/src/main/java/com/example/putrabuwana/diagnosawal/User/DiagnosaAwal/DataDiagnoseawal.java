package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import com.google.gson.annotations.SerializedName;

public class DataDiagnoseawal {

    @SerializedName("id")
    private String id;
    @SerializedName("deskripsi")
    private String deskripsi;

    public DataDiagnoseawal(String id, String deskripsi) {
        this.id = id;
        this.deskripsi = deskripsi;
    }


    public String getId() {
        return id;
    }
    public void setId(String id){this.id = id;}

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi){this.deskripsi = deskripsi;}
}
