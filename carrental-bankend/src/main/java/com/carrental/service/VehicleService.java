package com.carrental.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carrental.dto.VehicleAddDto;
import com.carrental.dto.VehicleFilterDto;
import com.carrental.model.Equipment;
import com.carrental.model.User;
import com.carrental.model.Vehicle;
import com.carrental.model.VehicleParameters;

public interface VehicleService {

	public List<Vehicle> getAllVehicles();
	
	public Page<Vehicle> getVehiclesForPage(Pageable pageable,Long userId);

	public Page<Vehicle> getBestOfferCars(Pageable pageable);

	public List<Vehicle> getAvailableVehicleListForLocation(Long cityId);

	public Vehicle getVehicleById(Long id);

	public Page<Vehicle> getFiltredCarListForPage(VehicleFilterDto vehicleFilter, Pageable pageable);

	public List<String> getBrandList();

	public List<String> getModelListForBrand(String brand);

	public List<String> getBodTypeList();

	public List<String> getCityList();

	public List<String> getColorList();

	public int updateVehicle(VehicleAddDto vehicleAddDto);
	
	public void addVehicle(VehicleAddDto vehicleAddDto);
	
	public void addEquipment(Equipment equipment, Long vehicleId);
	
	public void removeEquipment(String eqpCode, Long vehicleId);

}
