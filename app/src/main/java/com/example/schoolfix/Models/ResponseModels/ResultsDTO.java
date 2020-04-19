package com.example.schoolfix.Models.ResponseModels;


import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class ResultsDTO implements Serializable {

	@SerializedName("selected_option_id")
	private int selectedOptionId;

	@SerializedName("correct_option_id")
	private int correctOptionId;

	public void setSelectedOptionId(int selectedOptionId){
		this.selectedOptionId = selectedOptionId;
	}

	public int getSelectedOptionId(){
		return selectedOptionId;
	}

	public void setCorrectOptionId(int correctOptionId){
		this.correctOptionId = correctOptionId;
	}

	public int getCorrectOptionId(){
		return correctOptionId;
	}

	@Override
 	public String toString(){
		return 
			"ResultsDTO{" + 
			"selected_option_id = '" + selectedOptionId + '\'' + 
			",correct_option_id = '" + correctOptionId + '\'' + 
			"}";
		}
}