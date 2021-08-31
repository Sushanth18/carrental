package com.carrental.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.carrental.model.Location;

public interface LocationService {

	public Location getLocationById(Long id);
	
	public List<Location> getLocationList();

	public Page<Location> getLocationListForPage(Pageable pageable);
	
}
