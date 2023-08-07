package com.hero.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hero.dto.entity.Organisation;
import com.hero.dto.entity.SupeOrganisation;
import com.hero.dto.entity.compositeobjects.SightingSupeLocation;
import com.hero.model.service.OrganisationService;
import com.hero.model.service.SightingService;

@RestController
public class OrganisationController {

	@Autowired
	private OrganisationService organisationService;
	@Autowired 
	private SightingService sightingService;
	
	// Part 1 Requirement: The system must be able to report all of the members of a particular organisation.

	@GetMapping (path = "/organisation/members/org/{organisationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SupeOrganisation> getOrganisationMembers (@PathVariable("organisationId") int organisationId) {
		return organisationService.getOrganisationMembers(organisationId);
	}

	// Part 1 Requirement: The system must be able to report all of the organisations a particular superhero/villain belongs to.
	
	@GetMapping (path = "/organisation/members/supe/{supeId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SupeOrganisation> getOrganisationBySupeId (@PathVariable("supeId") int supeId) {
		return organisationService.getOrganisationBySupeId(supeId);
	}
	
	// Extra 
	
	@GetMapping (path = "/organisation/{organisationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Organisation> getOrganisationById (@PathVariable("organisationId") int organisationId) {
		Organisation organisation = organisationService.getOrganisationById(organisationId);
		if (organisation != null) {
			return ResponseEntity.ok(organisation);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
	
	// Part 2 Requirement: UI Layer - Home Page.

	@RequestMapping("/")
	public ModelAndView viewHomePage() {
		List<SightingSupeLocation> sightingInfo = sightingService.getTenRecentSightings();
		ModelAndView modelAndView = new ModelAndView("HomePage");
		modelAndView.addObject("sightingInfo", sightingInfo);
		return modelAndView;
	}
	
	 // Part 2 Requirement: UI Layer - Details Page for organisations.
	
	@RequestMapping("/organisationDetails")
	public ModelAndView listOrganisationDetails() {
		ModelAndView modelAndView = new ModelAndView("Organisation/OrganisationDetailsPage");
		return modelAndView;
	}
	
	// Part 2 Requirement: UI Layer - List Pages for Organisations.

	@RequestMapping("/listAllOrganisations")
	public ModelAndView getAllOrganisations() {
		ModelAndView modelAndView = new ModelAndView();

		List<Organisation> organisationList = organisationService.getAllOrganisations();
		modelAndView.addObject("organisationList", organisationList);
		modelAndView.setViewName("Organisation/ListAllOrganisationsPage");
		return modelAndView;
	}
	
	// Part 2 Requirement: UI Layer - A section exists for adding a new entity..
	
	@RequestMapping("/addOrganisationForm")
	public ModelAndView addOrganisationForm() {
	    ModelAndView modelAndView = new ModelAndView("Organisation/AddOrganisationPage");
	    modelAndView.addObject("organisation", new Organisation());
	    return modelAndView;
	}

	@RequestMapping("/addOrganisation")
	public ModelAndView addOrganisation(@ModelAttribute("organisation") Organisation organisation) {
		ModelAndView modelAndView = new ModelAndView();
	
		String message = null;
		if (organisationService.addOrganisation(organisation) != null)
			message = "Organisation Added";
		else
			message = "Organisation Not Added";

		modelAndView.addObject("outputMessage", message);
		modelAndView.setViewName("Organisation/AddOutput");

		return modelAndView;
	}
	
	// Part 2 Requirement: UI Layer - Edit pages for Organisations.
	
	@RequestMapping("/updateOrganisationForm")
	public ModelAndView updateOrganisationForm() {
	    ModelAndView modelAndView = new ModelAndView("Organisation/UpdateOrganisationPage");
	    List<Organisation> organisations = organisationService.getAllOrganisations();
	    List<Integer> organisationIds = organisations.stream()
	        .map(Organisation::getOrganisationId)
	        .collect(Collectors.toList());
	    modelAndView.addObject("organisation", new Organisation());
	    modelAndView.addObject("organisationIds", organisationIds);
	    return modelAndView;
	}

	@RequestMapping("/updateOrganisation")
	public ModelAndView updateOrganisation (
			@RequestParam("organisationId") int Id, 
			@RequestParam("organisationId") int organisationId,
			@RequestParam("organisationName") String organisationName,
			@RequestParam("organisationDescription") String organisationDescription,
			@RequestParam("organisationAddress") String organisationAddress,
			@RequestParam("organisationContactInfo") String organisationContactInfo)
	{
		String message = null;

		if(organisationService.updateOrganisation (
				organisationId, 
				organisationName, 
				organisationDescription, 
				organisationAddress, 
				organisationContactInfo))
			message="Organisation Updated";
		else
			message="Organisation Not Updated";
		
		return new ModelAndView("Organisation/updateOutput", "outputMessage", message);
	}

	// Part 2 Requirement: UI Layer - Delete pages + confirmation for Organisations.
	
	@RequestMapping("/deleteOrganisationForm")
	public ModelAndView deleteOrganisationForm() {
	    ModelAndView modelAndView = new ModelAndView("Organisation/DeleteOrganisationPage");
	    List<Organisation> organisations = organisationService.getAllOrganisations();
	    List<Integer> organisationIds = organisations.stream()
	        .map(Organisation::getOrganisationId)
	        .collect(Collectors.toList());
	    modelAndView.addObject("organisation", new Organisation());
	    modelAndView.addObject("organisationIds", organisationIds);
	    return modelAndView;
	}

	@RequestMapping("/deleteOrganisation")
	public ModelAndView deleteOrganisation(@ModelAttribute("organisation") Organisation organisation) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int organisationId = organisation.getOrganisationId();
		if (organisationService.deleteOrganisation(organisationId) != null) {
			message = "Organisation with ID '" + organisationId + "' Deleted.";
		} else {
			message = "Organisation with ID '" + organisationId + "' Not Deleted.";
		}
		modelAndView.addObject("outputMessage", message);
		modelAndView.setViewName("Organisation/deleteOutput");
		
		return modelAndView;
	}
	
}