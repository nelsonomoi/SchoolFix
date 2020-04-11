package com.example.schoolfix.Models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User
{
    @SerializedName("user_type")
    @Expose
    private Character user_type;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("username")
    @Expose
    private String username;

    public Character getUser_type ()
    {
        return user_type;
    }

    public void setUser_type (Character user_type)
    {
        this.user_type = user_type;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

}
