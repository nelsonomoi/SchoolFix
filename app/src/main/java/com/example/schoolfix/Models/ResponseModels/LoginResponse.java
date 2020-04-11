package com.example.schoolfix.Models.ResponseModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("token")
    @Expose
    private String token;

    public String getToken() {
        return token;
    }

}
