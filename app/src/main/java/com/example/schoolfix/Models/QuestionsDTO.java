package com.example.schoolfix.Models;

import java.util.List;
import java.io.Serializable;

public class QuestionsDTO implements Serializable {
	private List<List<List<String>>> questions;

	public void setQuestions(List<List<List<String>>> questions){
		this.questions = questions;
	}

	public List<List<List<String>>> getQuestions(){
		return questions;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsDTO{" + 
			"questions = '" + questions + '\'' + 
			"}";
		}
}