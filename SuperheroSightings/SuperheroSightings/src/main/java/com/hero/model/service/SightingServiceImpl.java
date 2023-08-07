package com.hero.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hero.dto.entity.Location;
import com.hero.dto.entity.Sighting;
import com.hero.dto.entity.Supe;
import com.hero.dto.entity.compositeobjects.SightingSupeLocation;
import com.hero.model.dao.SupeDao;
import com.hero.model.dao.SupeDaoImpl;
import com.hero.model.dao.LocationDao;
import com.hero.model.dao.LocationDaoImpl;
import com.hero.model.dao.SightingDao;
import com.hero.model.dao.SightingDaoImpl;

@Service
public class SightingServiceImpl implements SightingService {

  	@Autowired
	private SightingDao sightingDao;
  	@Autowired 
  	private LocationDao locationDao;
  	@Autowired 
  	private SupeDao supeDao;

	private boolean supeExists(Supe supe) {
        SupeDao supeDao = new SupeDaoImpl();
        Supe existingSupe = supeDao.getSupeById(supe.getSupeId());
        return existingSupe != null;
    }

    private boolean locationExists(Location location) {
        LocationDao locationDao = new LocationDaoImpl();
        Location existingLocation = locationDao.getLocationById(location.getLocationId());
        return existingLocation != null;
    }
    
	@Override
	public void recordSighting(Supe supe, Location location, int supeId, int locationId, Date dateSeen) {
	        if (supeExists(supe) && locationExists(location)) {
	            Sighting sighting = new Sighting();
	            sighting.setSupeId(supeId);
	            sighting.setLocationId(locationId);
	            sighting.setDateSeen(dateSeen);

	            SightingDao sightingDao = new SightingDaoImpl();
	            sightingDao.createSighting(sighting);

	            System.out.println("Sighting recorded successfully!");
	        } else {
	            System.out.println("Error: Hero or location does not exist in the database.");
	        }
	    }

	@Override
	public Sighting createSighting(Sighting sighting) {
		
		return sightingDao.createSighting(sighting);
	}

	@Override
	public Sighting getSightingById(int sightingId) {
		return sightingDao.getSightingById(sightingId);
	}
	
	@Override
	public List<SightingSupeLocation> getSightingInformationById(int sightingId) {
		
		List<Sighting> sightings = new ArrayList<>();

	    Sighting sighting = sightingDao.getSightingById(sightingId);
	    if (sighting != null) {
	        sightings.add(sighting);
	    }

	    return gatherSightingInformation(sightings);
	}

	@Override
	public Sighting updateSighting(int sightingId, String field, String newValue) {
		
		if(sightingDao.updateSighting(sightingId, field, newValue)>0) {
			return getSightingById(sightingId);
		}
		return null;
	}

	@Override
	public Sighting deleteSighting(int sightingId) {
		
		Sighting sighting = getSightingById(sightingId);
		
		if(sighting != null) {
			sightingDao.deleteSighting(sightingId);
		}
		return sighting;
	}

	@Override
	public List<SightingSupeLocation> getAllSightings() {	
		
		List<Sighting> sightings = sightingDao.getAllSightings();
		return gatherSightingInformation(sightings);
	}
	
	@Override
	public List<SightingSupeLocation> getTenRecentSightings() {	
		
		List<Sighting> sightings = sightingDao.getTenRecentSightings();
		return gatherSightingInformation(sightings);
	} 

	@Override
	public List<SightingSupeLocation> getSightingsByLocation(int locationId) {
		
		List<Sighting> sightings = sightingDao.getSightingsByLocation(locationId);
		return gatherSightingInformation(sightings); 
	}

	@Override
	public List<SightingSupeLocation> getSightingsBySupe(int supeId) {
		
		List<Sighting> sightings = sightingDao.getSightingsByHero(supeId);
		return gatherSightingInformation(sightings); 

	}


	@Override
	public List<SightingSupeLocation> getSightingInformationByDate(String date){
		
		List<Sighting> sightings = sightingDao.getSightingsByDate(date);
	
		return gatherSightingInformation(sightings);
		
	}
	
	/* A method which brings together information from Sighting, Supe, and Location tables
	 * When sightings are searched and then this is used, it will return the superhero name and 
	 * location name and coordinates instead of just the ids. */

	private List<SightingSupeLocation> gatherSightingInformation(List<Sighting> sightings){

		List<SightingSupeLocation> sightInfoList = new ArrayList<>();

		for(Sighting sighting: sightings) {

			int supeId = sighting.getSupeId();
			int locationid = sighting.getLocationId();

			Location location = locationDao.getLocationById(locationid);
			Supe supe = supeDao.getSupeById(supeId);

			SightingSupeLocation sightInfo = new SightingSupeLocation();
			sightInfo.setSightingId(sighting.getSightingId());
			sightInfo.setDateSeen(sighting.getDateSeen().toString());
			sightInfo.setSupeName(supe.getSupeName());
			sightInfo.setLocationName(location.getLocationName());
			sightInfo.setLocationCoordinates(location.getLocationCoordinates());

			sightInfoList.add(sightInfo);

		}

		return sightInfoList;
	}


}
