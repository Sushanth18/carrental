package com.carrental.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carrental.dto.VehicleAddDto;
import com.carrental.dto.VehicleFilterDto;
import com.carrental.model.Equipment;
import com.carrental.model.Vehicle;

public interface VehicleRepositoryCustom {

	public Vehicle getVehicleUsingId(Long id);

	public Page<Vehicle> getFiltredCarListForPage(VehicleFilterDto vehicleFilter, Pageable pageable);
	
	public List<Vehicle> getAvailableVehicleListForLocation(Long cityId);

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
