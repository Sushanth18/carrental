package com.carrental.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.model.User;
import com.carrental.model.UserRole;
import com.carrental.service.UserService;
import com.carrental.service.UserServiceImpl;

@RestController
@RequestMapping(value = { "/userdata" })
public class SecurityController {

	@Autowired
	UserServiceImpl userService;

	@RequestMapping(value = "/username", method = RequestMethod.GET)
	public Map<String, String> currentUserName(@RequestParam("username") String userName) {
		return Collections.singletonMap("username",userName);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public User currentUserData(@RequestParam("username") String userName) {

		return userService.getUserByLogin(userName);
	}

	@RequestMapping(value = "/userroles", method = RequestMethod.GET)
	public Map<String, String[]> currentUserRole(@RequestParam("username") String userName) {
		User user = userService.getUserByLogin(userName);
		List<String> rolesList = new ArrayList<>();
		Set<UserRole> roles = user.getUserRolesList();
		for (UserRole g : roles) {
			rolesList.add(g.getType());
		}
		return Collections.singletonMap("userroles", rolesList.toArray(new String[0]));
	}

	@RequestMapping(value = "/isauthenticated", method = RequestMethod.GET)
	public Map<String, Boolean> checkIfUserIsAuthenticated(@RequestParam("username") String userName) {
		if (userName!=null && !userName.isEmpty()) {
			return Collections.singletonMap("isAuthenticated", true);
		} else {
			return Collections.singletonMap("isAuthenticated", false);
		}
	}
}
