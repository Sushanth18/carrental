package com.carrental.controller;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

//import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.carrental.dto.UserRegistrationDto;
import com.carrental.exception.EmailExistsException;
import com.carrental.exception.LoginExistsException;
import com.carrental.model.User;
import com.carrental.service.UserServiceImpl;

@Controller
@RequestMapping(value = { "/registration" })
public class RegistrationController {

	@Autowired
	UserServiceImpl userService;

	@RequestMapping(method = RequestMethod.GET)
	public String registration(WebRequest request, Model model) {
		UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
		model.addAttribute("user", userRegistrationDto);
		model.addAttribute("standardDate", new Date());
		return "registration";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") UserRegistrationDto user) {

		JSONObject jsonObject = new JSONObject();
		if (!user.getPassword().equalsIgnoreCase(user.getPasswordMatches())) {
			jsonObject.put("success", 0);
			jsonObject.put("message", "Passwords don't match");
		} else {
			User registered = null;
			try {
				registered = userService.addUser(user);
				jsonObject.put("success", 1); 
			} catch (LoginExistsException el) {
				jsonObject.put("success", 0);
				jsonObject.put("message", "There is an account with that username");
			} catch (EmailExistsException ee) {
				jsonObject.put("success", 0);
				jsonObject.put("message", "There is an account with that email adress");
				
			}
		}
		return jsonObject.toString();
	}
}
