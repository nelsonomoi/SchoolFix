package com.example.schoolfix.Models.BodyParams;

public class PapersBodyParam {
    private int school_id;
    private int class_id;
    private int subject_id;

    public PapersBodyParam(int school_id, int class_id, int subject_id) {
        this.school_id = school_id;
        this.class_id = class_id;
        this.subject_id = subject_id;
    }
}
