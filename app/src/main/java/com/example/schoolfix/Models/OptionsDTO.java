package com.example.schoolfix.Models;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.Comparator;


public class OptionsDTO implements Serializable {

	@SerializedName("option_description")
	private String optionDescription;

	@SerializedName("option_id")
	private int optionId;

	public void setOptionDescription(String optionDescription){
		this.optionDescription = optionDescription;
	}

	public String getOptionDescription(){
		return optionDescription;
	}

	public void setOptionId(int optionId){
		this.optionId = optionId;
	}

	public int getOptionId(){
		return optionId;
	}

	@Override
 	public String toString(){
		return 
			"OptionsDTO{" + 
			"option_description = '" + optionDescription + '\'' + 
			",option_id = '" + optionId + '\'' + 
			"}";
		}
}