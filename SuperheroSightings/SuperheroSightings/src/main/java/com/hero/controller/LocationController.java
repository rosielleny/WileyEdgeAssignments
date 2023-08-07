package com.hero.controller;

import com.hero.dto.entity.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hero.model.service.*;

@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping("/locationDetails")
	public ModelAndView listLocationDetails() {
		ModelAndView modelAndView = new ModelAndView("Location/LocationDetails");
		return modelAndView;
	}
	
	@RequestMapping("/addLocationForm")
    public ModelAndView addLocationForm() {
        ModelAndView modelAndView = new ModelAndView("Location/InputLocationDetails");
        modelAndView.addObject("location", new Location());
        return modelAndView;
    }

    @RequestMapping("/addLocation")
    public ModelAndView addLocation(@ModelAttribute("location") Location location) {
        ModelAndView modelAndView = new ModelAndView();
        String message;

        Location createdLocation = locationService.createLocation(location);
        if (createdLocation != null) {
            message = "Location Added";
        } else {
            message = "Location Not Added";
        }

        modelAndView.addObject("outputMessage", message);
        modelAndView.setViewName("Location/AddOutput");
        return modelAndView;
    }
    
    @RequestMapping("/updateLocationForm")
    public ModelAndView updateLocationForm() {
        ModelAndView modelAndView = new ModelAndView("Location/InputLocationDetailsForUpdate");
        List<Location> locations = locationService.getAllLocations();
        List<Integer> locationIds = locations.stream()
            .map(Location::getLocationId)
            .collect(Collectors.toList());
        modelAndView.addObject("locationIds", locationIds);
        return modelAndView;
    }

    @RequestMapping("/updateLocation")
    public ModelAndView updateLocation(
            @RequestParam("locationId") int locationId,
            @RequestParam("newLocationName") String newLocationName) {
        String message;
        boolean isUpdated = locationService.updateLocation(locationId, newLocationName);
        if (isUpdated) {
            message = "Location Updated";
        } else {
            message = "Location Not Updated";
        }
        ModelAndView modelAndView = new ModelAndView("Location/UpdateLocationOutput");
        modelAndView.addObject("outputMessage", message);
        return modelAndView;
    }

    @RequestMapping("/deleteLocationForm")
    public ModelAndView deleteLocationForm() {
        ModelAndView modelAndView = new ModelAndView("Location/InputLocationIdForDelete");
        List<Location> locations = locationService.getAllLocations();
        List<Integer> locationIds = locations.stream()
                .map(Location::getLocationId)
                .collect(Collectors.toList());
        modelAndView.addObject("location", new Location());
        modelAndView.addObject("locationIds", locationIds);
        return modelAndView;
    }

    @RequestMapping("/deleteLocation")
    public ModelAndView deleteLocation(@ModelAttribute("location") Location location) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;
        int locationId = location.getLocationId();
        if (locationService.deleteLocation(locationId) != null) {
            message = "Location with ID '" + locationId + "' Deleted.";
        } else {
            message = "Location with ID '" + locationId + "' Not Deleted.";
        }
        modelAndView.addObject("outputMessage", message);
        modelAndView.setViewName("Location/DeleteLocationOutput");

        return modelAndView;
    }
    
    @GetMapping("/showAllLocations")
    public ModelAndView showAllLocations() {
        List<Location> locationList = locationService.getAllLocations();
        ModelAndView modelAndView = new ModelAndView("Location/DisplayAllLocations");
        modelAndView.addObject("locationList", locationList);
        return modelAndView;
    }
}
