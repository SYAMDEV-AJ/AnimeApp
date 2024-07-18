package com.example.testing.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("url")
    @Expose
    public String url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}