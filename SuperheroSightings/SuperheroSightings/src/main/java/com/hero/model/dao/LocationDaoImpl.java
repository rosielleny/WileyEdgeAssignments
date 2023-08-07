package com.hero.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Location;
import com.hero.model.dao.LocationMapper;

@Repository
public class LocationDaoImpl implements LocationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public Location createLocation(Location location) {
		try {
			jdbcTemplate.update ("INSERT INTO LOCATION VALUES(?,?,?,?,?)",
			location.getLocationId(), location.getLocationName(),
					location.getLocationDescription(), location.getLocationAddress(),
					location.getLocationCoordinates());
			return location;
		} catch (Exception ex) {
			return null;
		}
	}


	@Override
	public Location getLocationById(int locationId) {
		try {
			return jdbcTemplate.queryForObject("SELECT * FROM LOCATION WHERE LOCATIONID=?", new LocationMapper(), locationId);
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public int updateLocation(int locationId, String locationName) {
		try {
	    return jdbcTemplate.update("UPDATE LOCATION SET LOCATIONNAME=? WHERE LOCATIONID=?", locationName, locationId);
		 } catch (Exception ex) {
		        return 0;
		    }
		}
		
	@Override
	public int deleteLocation(int locationId) {
		try {
			return jdbcTemplate.update("DELETE FROM LOCATION WHERE LOCATIONID=?", locationId);
		} catch (Exception ex) {
			return 0;
		}
	}

	@Override
	public List<Location> getAllLocations() {
		return jdbcTemplate.query("SELECT * FROM LOCATION", new LocationMapper());
	}

}
