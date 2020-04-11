package com.example.schoolfix.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kids
{
    @SerializedName("school_id")
    @Expose
    private String school_id;

    @SerializedName("class_id")
    @Expose
    private String class_id;

    @SerializedName("kid_username")
    @Expose
    private String kid_username;

    public String getKid_username() {
        return kid_username;
    }

    public void setKid_username(String kid_username) {
        this.kid_username = kid_username;
    }

    public String getSchool_id() {
        return school_id;
    }

    public void setSchool_id(String school_id) {
        this.school_id = school_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getKid_firstname() {
        return kid_firstname;
    }

    public void setKid_firstname(String kid_firstname) {
        this.kid_firstname = kid_firstname;
    }

    public String getKid_lastname() {
        return kid_lastname;
    }

    public void setKid_lastname(String kid_lastname) {
        this.kid_lastname = kid_lastname;
    }

    @SerializedName("kid_firstname")
    @Expose
    private String kid_firstname;

    @SerializedName("kid_lastname")
    @Expose
    private String kid_lastname;

    @SerializedName("parent_name")
    @Expose
    private String parent_name;

    @SerializedName("parent_email")
    @Expose
    private String parent_email;

    @SerializedName("school_name")
    @Expose
    private String school_name;

    @SerializedName("class_name")
    @Expose
    private String class_name;

    @SerializedName("parent_mobile_no")
    @Expose
    private String parent_mobile_no;

    public String getParent_name ()
    {
        return parent_name;
    }

    public void setParent_name (String parent_name)
    {
        this.parent_name = parent_name;
    }

    public String getParent_email ()
    {
        return parent_email;
    }

    public void setParent_email (String parent_email)
    {
        this.parent_email = parent_email;
    }

    public String getSchool_name ()
    {
        return school_name;
    }

    public void setSchool_name (String school_name)
    {
        this.school_name = school_name;
    }

    public String getClass_name ()
    {
        return class_name;
    }

    public void setClass_name (String class_name)
    {
        this.class_name = class_name;
    }

    public String getParent_mobile_no ()
    {
        return parent_mobile_no;
    }

    public void setParent_mobile_no (String parent_mobile_no)
    {
        this.parent_mobile_no = parent_mobile_no;
    }

}

