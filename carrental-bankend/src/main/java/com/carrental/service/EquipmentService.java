package com.carrental.service;

import java.util.List;

import com.carrental.model.Equipment;

public interface EquipmentService {

	public List<Equipment> getEquipmentList();
	
	public List<Equipment> getUnexistingDistinctEquipmentListForVehicle(Long id);
	
	public void addEquipment(Equipment equipment);
	
	public void deleteEquipmentById(String eqpCode);
	
	public Equipment getEquipmentByCode(String equipmentCode);
	
}
