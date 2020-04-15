package com.example.schoolfix.Models;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class QuestionsDTO implements Serializable {

	@SerializedName("question_id")
	private int questionId;

	@SerializedName("subject_id")
	private int subjectId;

	@SerializedName("school_id")
	private int schoolId;

	@SerializedName("question")
	private String question;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("options")
	private List<OptionsDTO> options;

	@SerializedName("paper_id")
	private int paperId;


	public void setSubjectId(int subjectId){
		this.subjectId = subjectId;
	}

	public int getSubjectId(){
		return subjectId;
	}

	public void setSchoolId(int schoolId){
		this.schoolId = schoolId;
	}

	public int getSchoolId(){
		return schoolId;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setOptions(List<OptionsDTO> options){
		this.options = options;
	}

	public List<OptionsDTO> getOptions(){
		return options;
	}

	public void setPaperId(int paperId){
		this.paperId = paperId;
	}

	public int getPaperId(){
		return paperId;
	}

	public void setQuestionId(int questionId){
		this.questionId = questionId;
	}

	public int getQuestionId(){
		return questionId;
	}

	@Override
 	public String toString(){
		return 
			"QuestionsDTO{" + 
			"subject_id = '" + subjectId + '\'' + 
			",school_id = '" + schoolId + '\'' + 
			",question = '" + question + '\'' + 
			",class_id = '" + classId + '\'' + 
			",options = '" + options + '\'' + 
			",paper_id = '" + paperId + '\'' + 
			",question_id = '" + questionId + '\'' + 
			"}";
		}
}