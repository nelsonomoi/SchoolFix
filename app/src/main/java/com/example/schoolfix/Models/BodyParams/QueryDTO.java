package com.example.schoolfix.Models.BodyParams;

import java.io.Serializable;

public class QueryDTO implements Serializable {
	private String id;
	private String question_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(String question_id) {
		this.question_id = question_id;
	}

	@Override
 	public String toString(){
		return "{" + id+":"+question_id+ "}";
		}
}