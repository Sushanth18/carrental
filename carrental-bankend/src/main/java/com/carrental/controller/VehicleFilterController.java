package com.carrental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.dto.VehicleFilterDto;
import com.carrental.model.Vehicle;
import com.carrental.service.VehicleServiceImpl;

@RestController
@RequestMapping(value = { "/carlistsearch" })
public class VehicleFilterController {

	@Autowired
	VehicleServiceImpl vehicleService;

	@RequestMapping(method = RequestMethod.POST, params = { "page", "number" })
	public Page<Vehicle> getFiltredCarListForFirstPage(@RequestBody VehicleFilterDto carFilter,
			@RequestParam(value = "page") int page, @RequestParam(value = "number") int number) {
		System.out.println("Entered:::");
		return vehicleService.getFiltredCarListForPage(carFilter, PageRequest.of(page, number));
	}

	@RequestMapping(value = "/brandlist", method = RequestMethod.GET)
	public List<String> vehicleSearchGetBrandList() {

		return vehicleService.getBrandList();
	}

	@RequestMapping(value = "/modellist", method = RequestMethod.GET)
	public List<String> vehicleSearchGetModelListForBrand(@RequestBody String brand) {

		return vehicleService.getModelListForBrand(brand);
	}

	@RequestMapping(value = "/bodytypelist", method = RequestMethod.GET)
	public List<String> vehicleSearchGetBodTypeList() {

		return vehicleService.getBodTypeList();
	}

	@RequestMapping(value = "/citylist", method = RequestMethod.GET)
	public List<String> vehicleSearchGetCityList() {

		return vehicleService.getCityList();
	}

	@RequestMapping(value = "/colorlist", method = RequestMethod.GET)
	public List<String> vehicleSearchGetColorList() {

		return vehicleService.getColorList();
	}

	@RequestMapping(value = "/modelsforbrand", method = RequestMethod.POST)
	public List<String> getModelsForBrand(@RequestBody String brand) {
		return vehicleService.getModelListForBrand(brand);
	}
}
