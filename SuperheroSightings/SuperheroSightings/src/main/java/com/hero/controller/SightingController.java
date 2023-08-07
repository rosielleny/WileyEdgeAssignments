package com.hero.controller;
import com.hero.dto.entity.*;
import com.hero.dto.entity.compositeobjects.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.hero.model.service.*;


@RestController
public class SightingController {

	@Autowired
	private SightingService sightingService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private SupeService supeService;
	
	

	/* Sighting 
	 * Requirements: 
	 * The system must be able to report all sightings (hero and location) for a particular date.
	 * It must have a screen(s) to create, view, edit, and delete superhero/supervillain sighting 
	 * (superhero/supervillain, location, and time) in the system. */
	
	@RequestMapping("/SightingMain")
	public ModelAndView SightingMainPage() {
		return new ModelAndView("Sighting/SightingIndex");
	}

	// Searching sightings by date, location, hero, id, and displaying all
	
	@RequestMapping("/InputSightingSearchInfo")
	public ModelAndView InputSightingSearchInfo() {
		return new ModelAndView("Sighting/InputSightingSearchInfo");
	}



	@RequestMapping("/searchSighting")
	public ModelAndView searchSighting(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String field = request.getParameter("field");
		List<SightingSupeLocation> sightingInfo = new ArrayList<>();
		
		if(field.equals("location")) {
			sightingInfo = sightingService.getSightingsByLocation(Integer.parseInt(request.getParameter("locationId")));
		}
		else if(field.equals("date")) {
			sightingInfo = sightingService.getSightingInformationByDate(request.getParameter("date"));
		}
		else if(field.equals("supe")) {
			sightingInfo = sightingService.getSightingsBySupe(Integer.parseInt(request.getParameter("supeId")));
		}
		else if(field.equals("id")) {
			sightingInfo = sightingService.getSightingInformationById(Integer.parseInt(request.getParameter("sightingID")));
		}
		else if(field.equals("all")) {
			sightingInfo = sightingService.getAllSightings();
		}
		else{
			modelAndView.addObject("message", "No sightings found.");
			modelAndView.setViewName("Output");
		}


		if(sightingInfo!=null && sightingInfo.size() >= 1) {
			modelAndView.addObject("sightingInfo", sightingInfo);
			modelAndView.setViewName("Sighting/ShowSighting");
		}
		else {
			modelAndView.addObject("message", "No sightings found.");
			modelAndView.setViewName("Output");
		}

		return modelAndView;
	}

	// Sighting Deletion

	
	@RequestMapping("/InputSightingIdForDelete")
	public ModelAndView InputSightingIdForDelete() {
		return new ModelAndView("Sighting/InputSightingIdForDelete", "sighting", new Sighting());
	}

	@RequestMapping("/deleteSighting")
	public ModelAndView deleteSighting(@ModelAttribute("sighting") Sighting sighting) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int sightingId = sighting.getSightingId();
		if(sightingService.deleteSighting(sightingId) != null) {
			message = "Sighting" + sightingId + " deleted.";
		}else {
			message = "Sighting" + sightingId + " not found in database. No changes made.";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");

		return modelAndView;
	}
	
	// Sighting create
	@RequestMapping("/InputSightingDetails")
	public ModelAndView InputSightingDetailsPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("sighting", new Sighting());
		modelAndView.setViewName("Sighting/InputSightingDetails");
		return modelAndView;
		
	}
	@RequestMapping("/saveSighting")
	public ModelAndView saveDvd(@Valid @ModelAttribute("sighting")Sighting sighting, BindingResult results) {
		ModelAndView modelAndView = new ModelAndView();
		
		if(results.hasErrors()) {
			return new ModelAndView("Sighting/InputSightingDetails", "sighting", sighting);
		}
		String message = null;
		if(sightingService.createSighting(sighting) != null) {
			message = "Sighting Added";
		}else {
			message = "Sighting Not Added";
		}
		
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Output");
		
		return modelAndView;
	}

	// Sighting Update
	
	@ModelAttribute("sightingIds")
	public List<Integer> getSightingIds(){
		
		List<Integer> sightings = sightingService.getAllSightings()
				.stream()
				.map(SightingSupeLocation::getSightingId) 
				.collect(Collectors.toList()); 
		return sightings;
	}
	
	@ModelAttribute("locationIds")
	public List<String> getLocationIds() {
	    List<Location> locations = locationService.getAllLocations();
	    return locations.stream()
	            .map(location -> location.getLocationId() + " - " + location.getLocationName())
	            .collect(Collectors.toList());
	}
	
	@ModelAttribute("supeIds")
	public List<String> getSupeIds() {
	    List<Supe> supes = supeService.getAllSupes();
	    return supes.stream()
	            .map(supe -> supe.getSupeId() + " - " + supe.getSupeName())
	            .collect(Collectors.toList());
	}
	

	@RequestMapping("/InputForSightingUpdate")
	public ModelAndView InputSightingDetailsForUpdateController() {
		return new ModelAndView("Sighting/InputSightingDetailsForUpdate");
	}
	
	@RequestMapping("/updateSighting")
	public ModelAndView updateDighting(HttpServletRequest request) {
		
		String message = null;
		int sightingId = Integer.parseInt(request.getParameter("sightingID"));
		String field = request.getParameter("field");
		String newValue = null;
		
	    if (field.equals("location")) {
	        newValue = request.getParameter("locationId");
	    } else if (field.equals("date")) {
	        newValue = request.getParameter("date");
	    } else if (field.equals("supe")) {
	        newValue = request.getParameter("supeId");
	    }
		
		if(sightingService.updateSighting(sightingId, field, newValue)!=null) {
			message = field + " updated for " +sightingId+ ".";
		}else {
			message = "No changes made.";
		}
		
		return new ModelAndView("Output", "message", message);
	}

}