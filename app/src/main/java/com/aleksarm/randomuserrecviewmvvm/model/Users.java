package com.aleksarm.randomuserrecviewmvvm.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Users {

    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("dob")
    @Expose
    private Dob dob;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("picture")
    @Expose
    private Picture picture;

    public Users(Name name, String email, Dob dob, String phone, Picture picture) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phone = phone;
        this.picture = picture;
    }

    public Name getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }
    public Dob getDob() {
        return dob;
    }
    public String getPhone() {
        return phone;
    }
    public Picture getPicture() {
        return picture;
    }

    public Users(){}
}
