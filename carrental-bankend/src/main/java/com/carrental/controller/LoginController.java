package com.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.carrental.model.User;
import com.carrental.service.UserService;
import com.carrental.util.EncryptDecryptUtils;
import com.google.gson.JsonObject;

@Controller
@RequestMapping(value = { "/login" })
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
		JsonObject jsonObject = new JsonObject();
		User user = userService.getUserByLoginAndPassword(username, EncryptDecryptUtils.encrypt(password));
		if (user != null) {
			jsonObject.addProperty("username", user.getLogin());
			jsonObject.addProperty("user_id", user.getId());
			jsonObject.addProperty("login_success", true);
		} else {
			jsonObject.addProperty("login_success", false);
		}
		return jsonObject.toString();
	}

}
