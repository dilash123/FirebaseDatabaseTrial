package com.example.firebasedatabasetrial;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Students {
    public String name;
    public String contact;

    public Students() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Students(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }
}
