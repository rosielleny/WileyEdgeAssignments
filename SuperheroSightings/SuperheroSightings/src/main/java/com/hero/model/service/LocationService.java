package com.hero.model.service;

import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.hero.dto.entity.Location;

@Service
public interface LocationService {
	
	Location createLocation(Location location);
	Location getLocationById(int locationId);
	boolean updateLocation(int locationId, String newLocationName);
    Location deleteLocation(int locationId);
    List<Location> getAllLocations();

}
