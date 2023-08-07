package com.hero.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Sighting;
import com.hero.dto.entity.SupeOrganisation;

@Repository
public class SightingDaoImpl implements SightingDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Sighting createSighting(Sighting sighting) {
		
		String query = "INSERT INTO Sighting (dateSeen, supeId, locationId) VALUES (?, ?, ?)";
		
		try {
			jdbcTemplate.update(query, sighting.getDateSeen(), sighting.getSupeId(), sighting.getLocationId());
			return sighting;
		}catch(Exception ex) {
			return null;
		}
	}

	@Override
	public Sighting getSightingById(int sightingId) {
		
		Sighting sighting = jdbcTemplate.queryForObject("SELECT * FROM sighting WHERE sightingId =?", new SightingMapper(), sightingId);
		return sighting;
	}

	@Override
	public int updateSighting(int sightingId, String field, String newValue) {
		
		String query;
		
		if(field.equals("date")) {
			query = "UPDATE Sighting SET " + field + " = '" + newValue + "' WHERE sightingId = " + sightingId;
		}
		query = "UPDATE Sighting SET " + field + "Id = " + newValue + " WHERE sightingId = " + sightingId;
		System.out.println(query);
		try {
			return jdbcTemplate.update(query);
		}catch(Exception ex){
			return 0;
		}
	}

	@Override
	public int deleteSighting(int sightingId) {
		
		String query = "DELETE FROM sighting WHERE sightingId=?";
		
		try {
			return jdbcTemplate.update(query, sightingId);
		}catch(Exception ex) {
			return 0;
		}
	}

	@Override
	public List<Sighting> getAllSightings() {
		
		String query = "SELECT * FROM Sighting";
		return jdbcTemplate.query(query, new SightingMapper());
	}

	@Override
	public List<Sighting> getSightingsByLocation(int locationId) {
		
		String query = "SELECT * FROM Sighting WHERE locationId ="+locationId;
		return jdbcTemplate.query(query, new SightingMapper());
	}

	@Override
	public List<Sighting> getSightingsByHero(int supeId) {

		String query = "SELECT * FROM Sighting WHERE supeId ="+supeId;
		return jdbcTemplate.query(query, new SightingMapper());
	}

	@Override
	public List<Sighting> getSightingsByDate(String date) {
		
		String query = "SELECT * FROM Sighting WHERE dateSeen = '"+date +"'";
		return jdbcTemplate.query(query, new SightingMapper());
	}

	@Override
	public List<Sighting> getTenRecentSightings(){
		String query = "SELECT * FROM Sighting ORDER BY dateSeen DESC LIMIT 10";;
		return jdbcTemplate.query(query, new SightingMapper());}
}
