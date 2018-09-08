package com.example.martin.onlinegalary;

import android.widget.ProgressBar;

public class modelclassforauthentication {
    private String uid;
    private String email;
    private String name;

    public modelclassforauthentication(String name,String email,String uid  ) {
        this.uid = uid;
        this.email = email;
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
