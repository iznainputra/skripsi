package com.example.putrabuwana.diagnosawal;

import com.google.gson.annotations.SerializedName;

public class DataForgotUser {
    @SerializedName("no")
    private int no;
    @SerializedName("nama")
    private String nama;
    @SerializedName("no_tlp")
    private String no_tlp;
    @SerializedName("email")
    private String email;
    @SerializedName("tmp_lahir")
    private String tmp_lahir;
    @SerializedName("tgl_lahir")
    private String tgl_lahir;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("berat_badan")
    private String berat_badan;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;

    public DataForgotUser(int no, String nama, String no_tlp, String email, String tmp_lahir, String tgl_lahir, String alamat, String berat_badan, String username, String password) {
        this.no = no;
        this.nama = nama;
        this.no_tlp = no_tlp;
        this.email = email;
        this.tmp_lahir = tmp_lahir;
        this.tgl_lahir = tgl_lahir;
        this.alamat = alamat;
        this.berat_badan = berat_badan;
        this.username = username;
        this.password = password;

    }


    public int getNo() {
        return no;
    }
    public void setNo(int no){this.no = no;}

    public String getNama() {
        return nama;
    }
    public void setNama(String nama){this.nama = nama;}

    public String getNo_tlp() {
        return no_tlp;
    }
    public void setNo_tlp(String no_tlp){this.no_tlp = no_tlp;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email){this.email = email;}

    public String getTmp_lahir() {
        return tmp_lahir;
    }
    public void setTmp_lahir(String tmp_lahir){this.nama = tmp_lahir;}

    public String getTgl_lahir() {
        return tgl_lahir;
    }
    public void setTgl_lahir(String tgl_lahir){this.tgl_lahir = tgl_lahir;}

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat){this.alamat = alamat;}

    public String getBerat_badan() { return berat_badan; }
    public void setBerat_badan(String berat_badan){this.berat_badan = berat_badan;}

    public String getUsername() { return username; }
    public void setUsername(String username){this.username = username;}

    public String getPassword() { return password; }
    public void setPassword(String password){this.password = password;}
}
