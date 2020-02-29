package com.project.validator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.project.customvalidator.NotEmpty;
import com.project.customvalidator.Select;

public class AddDistrict {

	int id;
	
	@Pattern(regexp = "^[a-zA-Z]*$",message="* Enter Alphabet only")
	@NotEmpty(message="* Please enter district name")
	@Size(min=5,message="* Please enter minimum 5 character")
	String name;
	
	@NotEmpty(message="* Please enter district acronym")
	@Pattern(regexp = "^[a-zA-Z]*$",message="* Enter Alphabet only")
	@Size(min=2,max=5,message="* Please enter minimum 2 to maximum 5 character")
	String acronym;
	
	@Select(message="* Please select state")
	Integer state;
	
	boolean inn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public boolean isInn() {
		return inn;
	}

	public void setInn(boolean inn) {
		this.inn = inn;
	}

}
