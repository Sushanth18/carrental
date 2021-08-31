package com.carrental.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicle_parameters")
public class VehicleParameters implements Serializable {

	@Id
	@JsonIgnore
	@Column(name = "id")
	private Long vehicleID;

	@Column(name = "body_type")
	private String bodytype;

	@Column(name = "production_year")
	private Integer productionYear;

	@Column(name = "fuel_type")
	private String fuelType;

	@Column(name = "power")
	private Integer power;

	@Column(name = "gear_box")
	private String gearbox;

	@Column(name = "front_wheel_drive")
	private Integer frontWheelDrive;

	@Column(name = "doors_number")
	private Integer doorsNumber;

	@Column(name = "seats_number")
	private Integer seatsNumber;

	@Column(name = "color")
	private String color;

	@Column(name = "metallic")
	private Integer metallic;

	@Column(name = "photo_name")
	private String photoName;

	@Column(name = "description")
	private String description;


	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	public VehicleParameters() {
		super();
	}

	public VehicleParameters(Long vehicleID, String bodytype, Integer productionYear, String fuelType, Integer power,
			String gearbox, Integer frontWheelDrive, Integer doorsNumber, Integer seatsNumber, String color,
			Integer metallic, String photoName, String description) {
		super();
		this.vehicleID = vehicleID;
		this.bodytype = bodytype;
		this.productionYear = productionYear;
		this.fuelType = fuelType;
		this.power = power;
		this.gearbox = gearbox;
		this.frontWheelDrive = frontWheelDrive;
		this.doorsNumber = doorsNumber;
		this.seatsNumber = seatsNumber;
		this.color = color;
		this.metallic = metallic;
		this.photoName = photoName;
		this.description = description;
	}

	public Long getVehicleID() {
		return vehicleID;
	}

	public void setVehicleID(Long vehicleID) {
		this.vehicleID = vehicleID;
	}

	public String getBodytype() {
		return bodytype;
	}

	public void setBodytype(String bodytype) {
		this.bodytype = bodytype;
	}

	public Integer getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Integer productionYear) {
		this.productionYear = productionYear;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public Integer getPower() {
		return power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public String getGearbox() {
		return gearbox;
	}

	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	public Integer getFrontWheelDrive() {
		return frontWheelDrive;
	}

	public void setFrontWheelDrive(Integer frontWheelDrive) {
		this.frontWheelDrive = frontWheelDrive;
	}

	public Integer getDoorsNumber() {
		return doorsNumber;
	}

	public void setDoorsNumber(Integer doorsNumber) {
		this.doorsNumber = doorsNumber;
	}

	public Integer getSeatsNumber() {
		return seatsNumber;
	}

	public void setSeatsNumber(Integer seatsNumber) {
		this.seatsNumber = seatsNumber;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getMetallic() {
		return metallic;
	}

	public void setMetallic(Integer metallic) {
		this.metallic = metallic;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "VehicleParameters [vehicleID=" + vehicleID + ", bodytype=" + bodytype + ", productionYear="
				+ productionYear + ", fuelType=" + fuelType + ", power=" + power + ", gearbox=" + gearbox
				+ ", frontWheelDrive=" + frontWheelDrive + ", doorsNumber=" + doorsNumber + ", seatsNumber="
				+ seatsNumber + ", color=" + color + ", metallic=" + metallic + ", photoName=" + photoName + "]";
	}

}
