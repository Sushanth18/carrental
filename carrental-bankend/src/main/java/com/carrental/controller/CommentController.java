package com.carrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.model.Comment;
import com.carrental.service.CommentServiceImpl;
import com.carrental.service.StarsServiceImpl;
import com.carrental.service.VehicleServiceImpl;

@RestController
@RequestMapping(value = "/comments")
public class CommentController {
	
	@Autowired
	CommentServiceImpl commentService;
	

	@Autowired
	VehicleServiceImpl vehicleService;
	
	@RequestMapping(value = { "/{vehicleID}" }, method = RequestMethod.GET)
	public Page<Comment> getVehicleCommentsForPage(@PathVariable Long vehicleID, @RequestParam(value = "page") int page,@RequestParam(value = "number") int number) {
		return commentService.getCommentsForVehicle(vehicleID,PageRequest.of(page, number));
	}
	
	@RequestMapping(value = { "/all/{vehicleID}" }, method = RequestMethod.GET)
	public List<Comment> getAllVehicleComments(@PathVariable Long vehicleID) {
		return commentService.getAllForVehicle(vehicleID);
	}

	@RequestMapping(value = { "/{vehicleID}" }, method = RequestMethod.POST)
	public void addCommentForVehicle(@PathVariable Long vehicleID, @RequestBody Comment comment) {
		comment.setVehicle(vehicleService.getVehicleById(vehicleID));
		commentService.addComment(comment);
	}
}
