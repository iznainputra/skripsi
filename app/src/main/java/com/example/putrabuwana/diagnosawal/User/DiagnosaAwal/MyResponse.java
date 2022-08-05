package com.example.putrabuwana.diagnosawal.User.DiagnosaAwal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MyResponse {
    @SerializedName("details")
    @Expose
    private List<DataDiagnoseawal> details = null;
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private String message;
    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public List<DataDiagnoseawal> getDetails() {
        return details;
    }

    public void setDetails(List<DataDiagnoseawal> details) {
        this.details = details;
    }

}