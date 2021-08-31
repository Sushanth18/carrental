package com.carrental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.model.ChangesBooking;
import com.carrental.model.Vehicle;
import com.carrental.service.ChangesBookingServiceImpl;

@RestController
@RequestMapping(value = "/bookingchanges")
public class ChangesBookingController {

	@Autowired
	private ChangesBookingServiceImpl changesBookingService;

	@RequestMapping(method = RequestMethod.GET, params = { "page", "number" })
	public Page<ChangesBooking> getAllBookingChanges(@RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return changesBookingService.getChangesBookingsForPage(PageRequest.of(page, number));
	}
}