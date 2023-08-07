package com.hero.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hero.dto.entity.Location;
import com.hero.dto.entity.Organisation;
import com.hero.model.dao.LocationDao;
import com.hero.model.dao.LocationDaoImpl;

@Service
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;

	@Override
	public Location createLocation(Location location) {
		return locationDao.createLocation(location);
	}

	@Override
	public Location getLocationById(int locationId) {
		return locationDao.getLocationById(locationId);
	}

	@Override
	public boolean updateLocation(int locationId, String newLocationName) {
	    return locationDao.updateLocation(locationId, newLocationName) > 0;
	}
	
	@Override
	public Location deleteLocation(int locationId) {
		Location location = getLocationById(locationId);
		
		if(location != null)
			locationDao.deleteLocation(locationId);
		
		return location;
	}
	
	@Override
	public List<Location> getAllLocations() {
		return locationDao.getAllLocations();
	}

}
