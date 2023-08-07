package com.hero.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hero.dto.entity.Sighting;

public interface SightingDao {
	
    Sighting createSighting(Sighting sighting);
    Sighting getSightingById(int sightingId);
    int deleteSighting(int sightingId);
    List<Sighting> getAllSightings();
    List<Sighting> getSightingsByLocation(int locationId);
    List<Sighting> getSightingsByHero(int heroId);
    List<Sighting> getSightingsByDate(String date);
	int updateSighting(int sightingId, String field, String newValue);
	List<Sighting> getTenRecentSightings();
    

}
