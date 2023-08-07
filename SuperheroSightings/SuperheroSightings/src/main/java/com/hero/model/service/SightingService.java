package com.hero.model.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hero.dto.entity.Location;
import com.hero.dto.entity.Sighting;
import com.hero.dto.entity.Supe;
import com.hero.dto.entity.compositeobjects.SightingSupeLocation;


public interface SightingService {

    Sighting createSighting(Sighting sighting);
    Sighting getSightingById(int sightingId);
    Sighting deleteSighting(int sightingId);
    List<SightingSupeLocation> getAllSightings();
    List<SightingSupeLocation> getSightingsByLocation(int locationId);
	Sighting updateSighting(int sightingId, String field, String newValue);
	List<SightingSupeLocation> getSightingsBySupe(int supeId);
	List<SightingSupeLocation> getSightingInformationByDate(String date);
	void recordSighting(Supe supe, Location location, int supeId, int locationId, Date dateSeen);
	List<SightingSupeLocation> getSightingInformationById(int sightingId);
	List<SightingSupeLocation> getTenRecentSightings();

}
