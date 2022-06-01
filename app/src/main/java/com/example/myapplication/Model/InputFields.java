package com.example.myapplication.Model;

public class InputFields {
    private String emailid;


    public InputFields(String emailid,String mobileNumber){
        this.emailid = emailid;
        this.mobileNumber = mobileNumber;

    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    private String mobileNumber;
}
