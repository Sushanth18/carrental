package com.carrental.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carrental.exception.BookingUnavailableVehicleException;
import com.carrental.model.Booking;
import com.carrental.model.Vehicle;
import com.carrental.service.BookingServiceImpl;
import com.carrental.service.VehicleServiceImpl;

@RestController
@RequestMapping(value = { "/booking" })
public class BookingController {

	@Autowired
	BookingServiceImpl bookingService;

	@Autowired
	VehicleServiceImpl vehicleService;

	@RequestMapping(value = { "/reserve" }, method = RequestMethod.POST)
	public void addBooking(@RequestBody Booking booking) {
		System.out.println("Adding booking: " + booking.toString());

		try {
			bookingService.addBooking(booking);
		} catch (BookingUnavailableVehicleException e) {
			System.err.println(e.getMessage());
		}

	}

	@RequestMapping(value = { "/excelfile" }, method = RequestMethod.GET)
	public void getAllBookingsInExcelFormat(HttpServletResponse response) {
		response.setHeader("Content-Encoding", "UTF-8");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		List<Booking> bookingList = bookingService.getAllBookings();
		File xls = null;
		try {
			xls = bookingService.createExcelBookingListExelFile(bookingList);
			final FileInputStream in = new FileInputStream(xls);
			final OutputStream out = response.getOutputStream();

			final byte[] buffer = new byte[8192];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();

		} catch (IOException e) {
			System.err.println("Cannot create booking list file in exel format: " + e.getMessage());
		}
	}

	@RequestMapping(method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getAllBookingsForPage(@RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getBookingsForPage(PageRequest.of(page, number));
	}

	@RequestMapping(value = { "/reserved" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getReservedBookingsForPage(@RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getBookingsReservedForPage(PageRequest.of(page, number));
	}

	@RequestMapping(value = { "/rented" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getRentedBookingsForPage(@RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getBookingsRentedForPage(PageRequest.of(page, number));
	}

	@RequestMapping(value = { "/{userId}" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getAllUserBookingsForPage(@PathVariable Long userId, @RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getUserBookingsForPage(PageRequest.of(page, number), userId);
	}

	@RequestMapping(value = { "/reserved/{userId}" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getReservedUserBookingsForPage(@PathVariable Long userId,
			@RequestParam(value = "page") int page, @RequestParam(value = "number") int number) {
		return bookingService.getUserBookingsReservedForPage(PageRequest.of(page, number), userId);
	}

	@RequestMapping(value = { "/rented/{userId}" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getRentedUserBookingsForPage(@PathVariable Long userId, @RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getUserBookingsRentedForPage(PageRequest.of(page, number), userId);
	}

	@RequestMapping(value = { "/canceled" }, method = RequestMethod.GET, params = { "page", "number" })
	public Page<Booking> getCanceledBookingsForPage(@RequestParam(value = "page") int page,
			@RequestParam(value = "number") int number) {
		return bookingService.getBookingsCanceledForPage(PageRequest.of(page, number));
	}

	@RequestMapping(value = { "/rent/{id}" }, method = RequestMethod.PUT)
	public void bookingRent(@PathVariable Long id) {
		bookingService.bookingRent(id);
	}

	@RequestMapping(value = { "/return/{id}" }, method = RequestMethod.PUT)
	public void bookingReturn(@PathVariable Long id) {
		bookingService.bookingReturn(id);
	}

	@RequestMapping(value = { "/cancel/{id}" }, method = RequestMethod.PUT)
	public void bookingCancel(@PathVariable Long id) {
		bookingService.cancelBooking(id);
	}

	@RequestMapping(value = { "/cost" }, method = RequestMethod.POST)
	public Map<String, BigDecimal> countCost(@RequestBody Booking booking) {
		System.out.println(booking.toString());

		BigDecimal itemCost = BigDecimal.ZERO;
		BigDecimal totalCost = BigDecimal.ZERO;

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Timestamp stamp1 = new Timestamp(booking.getReceiptDate().getTime());
		Timestamp stamp2 = new Timestamp(booking.getReturnDate().getTime());

		java.util.Date date1;
		java.util.Date date2;
		long dateDiff = 0;

		try {
			date1 = formatter.parse(formatter.format(new Date(stamp1.getTime())));
			date2 = formatter.parse(formatter.format(new Date(stamp2.getTime())));

			dateDiff = date2.getTime() - date1.getTime();

			double diffDays = dateDiff;
			diffDays /= (1000 * 60 * 60 * 24);

			int daysCeil = (int) Math.ceil(diffDays);

			Vehicle selectedVehicle = vehicleService.getVehicleById(booking.getVehicleId());
			itemCost = selectedVehicle.getDailyFee();

			totalCost = itemCost.multiply(BigDecimal.valueOf(daysCeil));

			return Collections.singletonMap("fullCost", totalCost);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;

	}
}
