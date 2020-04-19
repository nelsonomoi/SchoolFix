package com.example.schoolfix.Models.BodyParams;

import java.util.List;
import java.io.Serializable;

public class SubmitDTO implements Serializable {
	private int schoolId;
	private int classId;
	private int subjectId;
	private int paperId;
	private String kidUsername;
	private List<QueryDTO> query;
	private List<AnswerDTO> answer;

	public void setSchoolId(int schoolId){
		this.schoolId = schoolId;
	}

	public int getSchoolId(){
		return schoolId;
	}

	public void setClassId(int classId){
		this.classId = classId;
	}

	public int getClassId(){
		return classId;
	}

	public void setSubjectId(int subjectId){
		this.subjectId = subjectId;
	}

	public int getSubjectId(){
		return subjectId;
	}

	public void setPaperId(int paperId){
		this.paperId = paperId;
	}

	public int getPaperId(){
		return paperId;
	}


	public void setKidUsername(String kidUsername){
		this.kidUsername = kidUsername;
	}

	public String getKidUsername(){
		return kidUsername;
	}

	public void setQuery(List<QueryDTO> query){
		this.query = query;
	}

	public List<QueryDTO> getQuery(){
		return query;
	}

	public void setAnswer(List<AnswerDTO> answer){
		this.answer = answer;
	}

	public List<AnswerDTO> getAnswer(){
		return answer;
	}

	@Override
 	public String toString(){
		return 
			"{"+
			"school_id : '" + schoolId + '\'' +
			",class_id : '" + classId + '\'' +
			",subject_id : '" + subjectId + '\'' +
			",paper_id : '" + paperId + '\'' +
			",kid_username : '" + kidUsername + '\'' +
			",query : " + query + '\'' +
			",answer : " + answer + '\'' +
			"}";
		}
}