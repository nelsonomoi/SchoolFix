package com.example.schoolfix.Models.ResponseModels;

import com.example.schoolfix.Models.Subject;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;


public class SubjectResponse implements Serializable {

	@SerializedName("total_papers")
	private int totalPapers;

	@SerializedName("full_image_url")
	private String image_url;

	@SerializedName("subject")
	private Subject subject;

	public void setTotalPapers(int totalPapers){
		this.totalPapers = totalPapers;
	}

	public int getTotalPapers(){
		return totalPapers;
	}

	public void setSubject(Subject subject){
		this.subject = subject;
	}

	public Subject getSubject(){
		return subject;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	@Override
 	public String toString(){
		return 
			"SubjectResponse{" + 
			"total_papers = '" + totalPapers + '\'' + 
			",subject = '" + subject + '\'' + 
			"}";
		}
}