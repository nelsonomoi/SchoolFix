package com.example.schoolfix.Models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class PapersDTO implements Serializable {

	@SerializedName("subject_id")
	private int subjectId;

	@SerializedName("paper_name")
	private String paperName;

	@SerializedName("school_id")
	private int schoolId;

	@SerializedName("class_id")
	private int classId;

	@SerializedName("time_to_take")
	private String timeToTake;

	@SerializedName("subject_name")
	private String subjectName;

	@SerializedName("school_name")
	private String schoolName;

	@SerializedName("paper_id")
	private int paperId;

	@SerializedName("class_name")
	private String className;

	public void setSubjectId(int subjectId){
		this.subjectId = subjectId;
	}

	public int getSubjectId(){
		return subjectId;
	}

	public void setPaperName(String paperName){
		this.paperName = paperName;
	}

	public String getPaperName(){
		return paperName;
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

	public void setTimeToTake(String timeToTake){
		this.timeToTake = timeToTake;
	}

	public String getTimeToTake(){
		return timeToTake;
	}

	public void setSubjectName(String subjectName){
		this.subjectName = subjectName;
	}

	public String getSubjectName(){
		return subjectName;
	}

	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}

	public String getSchoolName(){
		return schoolName;
	}

	public void setPaperId(int paperId){
		this.paperId = paperId;
	}

	public int getPaperId(){
		return paperId;
	}

	public void setClassName(String className){
		this.className = className;
	}

	public String getClassName(){
		return className;
	}

	@Override
 	public String toString(){
		return 
			"PapersDTO{" + 
			"subject_id = '" + subjectId + '\'' + 
			",paper_name = '" + paperName + '\'' + 
			",school_id = '" + schoolId + '\'' + 
			",class_id = '" + classId + '\'' + 
			",time_to_take = '" + timeToTake + '\'' + 
			",subject_name = '" + subjectName + '\'' + 
			",school_name = '" + schoolName + '\'' + 
			",paper_id = '" + paperId + '\'' + 
			",class_name = '" + className + '\'' + 
			"}";
		}
}