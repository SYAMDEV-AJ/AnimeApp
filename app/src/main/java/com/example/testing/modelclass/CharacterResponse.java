package com.example.testing.modelclass;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CharacterResponse {

    @SerializedName("info")
    @Expose
    public Info info;
    @SerializedName("results")
    @Expose
    public ArrayList<Result> results =null;



    public class Result implements Serializable {


        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("species")
        @Expose
        public String species;
        @SerializedName("gender")
        @Expose
        public String gender;
        @SerializedName("location")
        @Expose
        public Location location;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("created")
        @Expose
        public String created;

        public String getCreated() {
            return created;
        }

        public String getGender() {
            return gender;
        }

        public Integer getId() {
            return id;
        }

        public String getImage() {
            return image;
        }

        public Location getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }

        public String getSpecies() {
            return species;
        }

        public String getStatus() {
            return status;
        }

        public String getUrl() {
            return url;
        }
    }







    public Info getInfo() {
        return info;
    }

    public List<Result> getResults() {
        return results;
    }
}