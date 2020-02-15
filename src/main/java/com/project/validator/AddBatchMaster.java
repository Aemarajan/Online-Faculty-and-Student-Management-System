package com.project.validator;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.project.customvalidator.NotEmpty;

public class AddBatchMaster {
	
	int id;
	
	@NotEmpty(message="* Please Enter the From year...")
	@Size(max=4,message="* Please Enter the 4 Digit Years...")
	@Pattern(regexp="[0-9]+",message="* Please Enter the Numbers Only...")
	String from_year;
	
	@NotEmpty(message="* Please Enter the To year...")
	@Size(max=4,message="* Please Enter the 4 Digit Years...")
	@Pattern(regexp="[0-9]+",message="* Please Enter the Numbers Only...")
	String to_year;
	
	boolean inn;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrom_year() {
		return from_year;
	}
	public void setFrom_year(String from_year) {
		this.from_year = from_year;
	}
	public String getTo_year() {
		return to_year;
	}
	public void setTo_year(String to_year) {
		this.to_year = to_year;
	}
	public boolean isInn() {
		return inn;
	}
	public void setInn(boolean inn) {
		this.inn = inn;
	}
}
