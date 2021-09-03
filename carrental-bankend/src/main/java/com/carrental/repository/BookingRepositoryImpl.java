package com.carrental.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.carrental.exception.BookingUnavailableVehicleException;
import com.carrental.model.Booking;
import com.carrental.model.Location;
import com.carrental.model.User;
import com.carrental.model.Vehicle;
import com.carrental.service.LocationService;
import com.carrental.service.UserService;

//@Repository
public class BookingRepositoryImpl implements BookingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LocationService locationService;

	@Override
	@Transactional
	public void addBooking(Booking booking) throws BookingUnavailableVehicleException {
		Vehicle reservedVehicle = vehicleRepository.getVehicleUsingId(booking.getVehicleId());
		booking.setVehicleName(reservedVehicle.getBrand());
		User user=userService.getUserById(booking.getUserId());
		booking.setUserName(user.getName());
		
		Location location=locationService.getLocationById(booking.getLocationId());
		booking.setLocationName(location.getCity());
		
		if (reservedVehicle.getVehicleStatus() == "UAV") {
			throw new BookingUnavailableVehicleException("Cannot book unavailable vehicle.");
		} else {
			reservedVehicle.setVehicleStatus("UAV");

			entityManager.merge(reservedVehicle);
			
			entityManager.persist(booking);
		}
	}

	@Override
	@Transactional
	public void cancelBooking(Long bookingId) {
		Booking booking = bookingRepository.getBookingsById(bookingId);

		Vehicle reservedVehicle = vehicleRepository.getVehicleUsingId(booking.getVehicleId());

		reservedVehicle.setVehicleStatus("AVI");

		booking.setBookingStateCode("CAN");

		entityManager.merge(booking);

		entityManager.merge(reservedVehicle);
	}

	@Override
	@Transactional
	public void bookingRent(Long bookingId) {
		Booking booking = bookingRepository.getBookingsById(bookingId);

		booking.setBookingStateCode("REN");

		entityManager.merge(booking);

	}

	@Override
	@Transactional
	public void bookingReturn(Long bookingId) {
		Booking booking = bookingRepository.getBookingsById(bookingId);

		Vehicle reservedVehicle = vehicleRepository.getVehicleUsingId(booking.getVehicleId());

		reservedVehicle.setVehicleStatus("AVI");

		booking.setBookingStateCode("RET");

		entityManager.merge(booking);

		entityManager.merge(reservedVehicle);
	}

}
