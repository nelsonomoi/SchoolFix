package com.example.schoolfix.Models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Subject implements Serializable {

	@SerializedName("subject_id")
	private int subjectId;

	@SerializedName("subject_code")
	private String subjectCode;

	@SerializedName("school_id")
	private int schoolId;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("description")
	private String description;

	public void setSubjectId(int subjectId){
		this.subjectId = subjectId;
	}

	public int getSubjectId(){
		return subjectId;
	}

	public void setSubjectCode(String subjectCode){
		this.subjectCode = subjectCode;
	}

	public String getSubjectCode(){
		return subjectCode;
	}

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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"Subject{" + 
			"subject_id = '" + subjectId + '\'' + 
			",subject_code = '" + subjectCode + '\'' + 
			",school_id = '" + schoolId + '\'' + 
			",class_id = '" + classId + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}