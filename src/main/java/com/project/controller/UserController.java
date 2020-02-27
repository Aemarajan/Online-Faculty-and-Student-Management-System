package com.project.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.User;
import com.project.service.MailService;
import com.project.service.PrivilegeService;
import com.project.service.UserService;
import com.project.validator.OTP;
import com.project.validator.SignIn;
import com.project.validator.SignUp;

@Controller
public class UserController {
	
	int otp;
	User userLoc;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	PrivilegeService privilegeService;
	
	@RequestMapping("SignUp")
	public String getSignup(ModelMap model) {
		model.addAttribute("signup", new SignUp());
		return "SignUp";
	}
	
	@RequestMapping("VerificationForm")
	public ModelAndView getOTPVerification(@Valid @ModelAttribute("signup") SignUp signup,BindingResult result,ModelMap mm) {
		if(result.hasErrors()) {
			return new ModelAndView("SignUp");
		}
		ModelAndView model = new ModelAndView();
		User user = new User();
		user.setEmail(signup.getEmail());
		user.setPassword(signup.getPassword());
		user.setUsername(signup.getUsername());
		user.setPrivilegeProvide(0);
		Random rand = new Random();
		otp = 100000 + rand.nextInt(900000);
		userLoc = user;
		User userExist = userService.findByEmail(user.getEmail());
		if(userExist == null) {
			mailService.sendEmail(user,otp);
			model.setViewName("OTP");
			mm.addAttribute("otp", new OTP());
			model.addObject("email", signup.getEmail());
		}else {
			model.setViewName("SignUp");
			model.addObject("exist", "Email id already registerd");
		}
		return model;
	}
	
	@RequestMapping("Verify")
	public ModelAndView verifyUser(@Valid @ModelAttribute("otp") OTP usotp,BindingResult result) {
		if(result.hasErrors()) {
			return new ModelAndView("OTP");
		}
		ModelAndView model = new ModelAndView();
		if(otp == usotp.getOtp()) {
			model.setViewName("SignUp");
			model.addObject("msg", "success");
			mailService.sendDetails(userLoc);
			userService.createUser(userLoc);
		}else {
			model.setViewName("OTP");
			model.addObject("msg", "Invalid OTP, Please try later.");
		}
		return model;
	}
	
	@RequestMapping("Login")
	public ModelAndView checkUser(@Valid @ModelAttribute("signin")SignIn signin,BindingResult result,HttpSession session) {
		if(result.hasErrors()) {
			return new ModelAndView("SignIn");
		}
		ModelAndView model = new ModelAndView();
		try {
			User userloc = userService.findByEmail(signin.getEmail());
			if(userloc.getPassword().equals(signin.getPassword())) {
				session.setAttribute("name", userloc.getUsername());
				session.setAttribute("id", userloc.getUser_id());
				if(userloc.getPrivilegeProvide() == 0) {
					model.setViewName("redirect:/Gallery");
				}else {
					model.setViewName("redirect:/home");
				}
			}else {
				model.setViewName("SignIn");
				model.addObject("msg", "fail");
			}
		}catch(NullPointerException e) {
			model.setViewName("SignIn");
			model.addObject("msg", "fail");
		}
		return model;
	}
	
	@RequestMapping("logout")
	public ModelAndView logout(@RequestParam(value="session",required=false)String s,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(!(s == null))
			mv.addObject("session", "destroy");
		session.invalidate();
		mv.setViewName("redirect:/SignIn");
		return mv;
	}
	
}
