package com.carrental.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vehicles")
public class Vehicle implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "registration")
	private String registration;

	@Column(name = "brand")
	private String brand;

	@Column(name = "model")
	private String model;

	@Column(name = "daily_fee")
	private BigDecimal dailyFee;

	@Column(name = "location_id")
	private Long locationId;

	@Column(name = "vehicle_status")
	private String vehicleStatus;

	@Column(name = "best_offer")
	private Boolean bestOffer;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinTable(name = "eqp", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipmentList = new ArrayList<Equipment>();

	@OneToOne(mappedBy = "vehicle")
	private VehicleParameters vehicleParameters;

	@JsonIgnore
	@OneToMany(mappedBy = "vehicle")
	private Set<Comment> comments;
	
	@Column(name = "location_name")
	private String locationName;

	@Column(name = "user_id")
	private Long userId;

	public Vehicle() {
		super();
	}

	public Vehicle(String registration, String brand, String model, BigDecimal dailyFee, Long locationId,
			String vehicleStatus, Boolean bestOffer,Long userId) {
		super();
		this.registration = registration;
		this.brand = brand;
		this.model = model;
		this.dailyFee = dailyFee;
		this.locationId = locationId;
		this.vehicleStatus = vehicleStatus;
		this.bestOffer = bestOffer;
		this.userId = userId;
	}

	public Vehicle(Long id) {
		this.id = id;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getDailyFee() {
		return dailyFee;
	}

	public void setDailyFee(BigDecimal dailyFee) {
		this.dailyFee = dailyFee;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public Boolean getBestOffer() {
		return bestOffer;
	}

	public void setBestOffer(Boolean bestOffer) {
		this.bestOffer = bestOffer;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public VehicleParameters getVehicleParameters() {
		return vehicleParameters;
	}

	public void setVehicleParameters(VehicleParameters vehicleParameters) {
		this.vehicleParameters = vehicleParameters;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	
	public String getLocationName() {
		return locationName;
	}
	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", registration=" + registration + ", brand=" + brand + ", model=" + model
				+ ", dailyFee=" + dailyFee + ", locationId=" + locationId + ", vehicleStatus=" + vehicleStatus
				+ ", bestOffer=" + bestOffer + ", equipmentList=" + equipmentList + ", vehicleParameters="
				+ vehicleParameters + "]";
	}

}