package com.carrental.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.entity.UserEntity;
import com.carrental.repository.UserRepositoryOld;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class UserController {

	@Autowired
	private UserRepositoryOld userRepository;

	/*-------------------- Group (Public) chat--------------------*/
	@GetMapping(value = "/users")
	public String users(@RequestParam("user_id") Integer userId) {
		List<UserEntity> fetchAllUser = userRepository.fetchAllUser(userId);
		List<JsonObject> jsonObjects = new ArrayList<>();
		for (UserEntity userEntity : fetchAllUser) {
			jsonObjects.add(putUserData(userEntity));
		}
		return jsonObjects.toString();
	}

	@GetMapping(value = "/user/username")
	public String users(@RequestParam("user_name") String userName) {
		return putUserData(userRepository.findByFirstName(userName)).toString();
	}

	private JsonObject putUserData(UserEntity userEntity) {
		JsonObject object = new JsonObject();
		object.addProperty("name", userEntity.getFirstName());
		object.addProperty("token", userEntity.getWsToken());
		object.addProperty("id", userEntity.getId());
		return object;
	}

}
