package com.aleksarm.randomuserrecviewmvvm.remote;

import com.aleksarm.randomuserrecviewmvvm.model.Users;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class UserList {

    @SerializedName("results")
    @Expose
    private ArrayList<Users> users = new ArrayList<>();

    public ArrayList<Users> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<Users> users) {
        this.users = users;
    }
}
