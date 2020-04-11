package com.example.schoolfix.Models.BodyParams;

public class QuestionBodyParam {
    private int school_id;
    private int class_id;
    private int subject_id;
    private int paper_id;

    public QuestionBodyParam(int school_id, int class_id, int subject_id, int paper_id) {
        this.school_id = school_id;
        this.class_id = class_id;
        this.subject_id = subject_id;
        this.paper_id = paper_id;
    }
}
