
package com.example.phoenix.tvmazeexamplepresent.models.film_detail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image_ {

    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("original")
    @Expose
    private String original;

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

}
