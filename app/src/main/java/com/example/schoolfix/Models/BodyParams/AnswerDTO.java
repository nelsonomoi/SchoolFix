package com.example.schoolfix.Models.BodyParams;

import java.io.Serializable;

public class AnswerDTO implements Serializable {
	private String question_id;
	private String option_id;

	public String getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}

	public String getOption_id() {
		return option_id;
	}

	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}

	@Override
	public String toString(){
		return
				"{"+question_id+":"+option_id+"}";
	}

}