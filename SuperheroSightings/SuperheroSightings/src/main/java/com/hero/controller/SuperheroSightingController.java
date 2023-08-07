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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.hero.model.service.*;


@RestController
public class SuperheroSightingController {

	@Autowired
	private SightingService sightingService;
	@Autowired
	private LocationService locationService;
	@Autowired
	private SupeService supeService;
	
	

// Get superheroes at location
	
	@GetMapping(path = "/sightings/location/{locationId}/superheroes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> getSuperheroesAtLocation(@PathVariable("locationId") String locationId) {
	    List<String> superheroes = supeService.getSuperheroesAtLocation(locationId);
	
	    if (!superheroes.isEmpty()) {
	        return new ResponseEntity<List<String>>(superheroes, HttpStatus.OK);
	    } else {
	        return new ResponseEntity<List<String>>(HttpStatus.NOT_FOUND);
	    }
	}

}


