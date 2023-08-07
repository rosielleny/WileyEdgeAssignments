package com.hero.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Location;

public interface LocationDao {
	
	Location createLocation(Location location);
    Location getLocationById(int locationId);
    int updateLocation(int locationId, String locationName);
    int deleteLocation(int locationId);
    List<Location> getAllLocations();

}
