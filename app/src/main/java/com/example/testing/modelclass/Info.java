package com.example.testing.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("pages")
    @Expose
    public Integer pages;
    @SerializedName("next")
    @Expose
    public String next;
    @SerializedName("prev")
    @Expose
    public Object prev;

    public Integer getCount() {
        return count;
    }

    public String getNext() {
        return next;
    }

    public Integer getPages() {
        return pages;
    }

    public Object getPrev() {
        return prev;
    }
}