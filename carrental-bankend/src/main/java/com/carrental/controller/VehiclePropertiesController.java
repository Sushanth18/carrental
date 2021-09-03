package com.carrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.dto.UserDetail;
import com.carrental.model.Comment;
import com.carrental.model.Location;
import com.carrental.model.User;
import com.carrental.model.Vehicle;
import com.carrental.service.LocationService;
import com.carrental.service.UserService;
import com.carrental.service.VehicleServiceImpl;

@RestController
@RequestMapping(value = { "/carlist/{id}" })
public class VehiclePropertiesController {

	@Autowired
	LocationService locationService;

	
	@Autowired
	VehicleServiceImpl vehicleService;

	@RequestMapping(method = RequestMethod.GET)
	public Vehicle getVehicleProperties(@PathVariable Long id) {

		Vehicle vehicle = vehicleService.getVehicleById(id);
		Location locationById = locationService.getLocationById(vehicle.getLocationId());
		vehicle.setLocationName(locationById.getCity());
		return vehicle; 
	}

}
