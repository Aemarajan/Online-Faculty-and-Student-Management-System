package com.project.validator;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.project.customvalidator.NotEmpty;

public class OTP {
	
	int id;
	
	@NotEmpty(message="* Please Enter the OTP")
	@Min(value=100000,message="* OTP length should be 6")
	@Max(value=999999,message="* OTP length should be 6")
	Integer otp;
	
	public Integer getOtp() {
		return otp;
	}
	
	public void setOtp(Integer otp) {
		this.otp = otp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
