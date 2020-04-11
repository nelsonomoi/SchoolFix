package com.example.schoolfix.Models.BodyParams;

public class SubjectBodyParam {
    private String school_id;
    private String class_id;
    private  String kid_username;

    public SubjectBodyParam(String school_id, String class_id,String kid_username) {
        this.school_id = school_id;
        this.class_id = class_id;
        this.kid_username=kid_username;
    }
}
