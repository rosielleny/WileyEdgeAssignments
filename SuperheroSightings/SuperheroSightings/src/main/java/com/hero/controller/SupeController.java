package com.hero.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hero.dto.entity.Location;
import com.hero.dto.entity.Supe;
import com.hero.model.service.SupeService;

@RestController
public class SupeController {
	
	@Autowired
	private SupeService supeService;
	
//	Main index page for the superheroes/supervillains database
	@RequestMapping("/supesIndex")
	public ModelAndView supesIndex() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("Supe/SupesIndex");
	    return modelAndView;
	}
	
//	It must have a screen(s) to view superheroes/supervillains in the system.
	@RequestMapping("/supesView")
	public ModelAndView displayAllSupes() {
	    List<Supe> supes = supeService.getAllSupes();
	    return new ModelAndView("Supe/supesView", "supes", supes);
	}
	
//	It must have a screen(s) to edit superheroes/supervillains in the system.
    @RequestMapping("/updateSupeForm")
    public ModelAndView updateSupeForm() {
        ModelAndView modelAndView = new ModelAndView("Supe/InputSupeDetailsForUpdate");
	    List<Supe> supes = supeService.getAllSupes();
        List<Integer> supeIds = supes.stream()
                .map(Supe::getSupeId)
                .collect(Collectors.toList());
        modelAndView.addObject("supeIds", supeIds);
        return modelAndView;
    }

    @RequestMapping("/updateSupe")
    public ModelAndView updateSupe(
            @RequestParam("supeId") int supeId,
            @RequestParam("newSupeName") String newSupeName) {
        ModelAndView modelAndView = new ModelAndView();
        Supe updatedSupe = supeService.updateSupe(supeId, newSupeName);

        if (updatedSupe != null) {
            String message = "Superhero/villian with ID " + supeId + " updated successfully.";
            modelAndView.addObject("message", message);
        } else {
            String message = "Failed to update Superhero/villian with ID " + supeId + ".";
            modelAndView.addObject("message", message);
        }

        modelAndView.setViewName("Supe/UpdateSupeOutput");
        return modelAndView;
    }

	@RequestMapping("/addSupeForm")
    public ModelAndView addSupeForm() {
        ModelAndView modelAndView = new ModelAndView("Supe/InputSupeDetails");
        modelAndView.addObject("supe", new Supe());
        return modelAndView;
    }

    @RequestMapping("/addSupe")
    public ModelAndView addSupe(@ModelAttribute("supe") Supe supe) {
        ModelAndView modelAndView = new ModelAndView();
        String message;
        Supe createdSupe = supeService.createSupe(supe);
        if (createdSupe != null) {
            message = "Superhero/villain Added";
        } else {
            message = "Superhero/villain Not Added";
        }

        modelAndView.addObject("message", message);
        modelAndView.setViewName("Supe/Output");
        return modelAndView;
    }

//	It must have a screen(s) to delete superheroes/supervillains in the system.
    @RequestMapping("/inputSupeIdForDelete")
    public ModelAndView deleteSuperheroForm() {
        ModelAndView modelAndView = new ModelAndView("Supe/InputSupeIdForDelete");
	    List<Supe> supes = supeService.getAllSupes();
        List<Integer> supeIds = supes.stream()
                .map(Supe::getSupeId)
                .collect(Collectors.toList());
        modelAndView.addObject("supe", new Supe());
        modelAndView.addObject("supeIds", supeIds);
        return modelAndView;
    }
    
	@RequestMapping("/deleteSupe")
	public ModelAndView deleteSupe(@ModelAttribute("supe") Supe supe) {
	    ModelAndView modelAndView = new ModelAndView();
	    String message = null;
	    int supeId = supe.getSupeId();
	    if (supeService.deleteSupe(supeId)) {
	        message = "Supe " + supeId + " deleted.";
	    } else {
	        message = "Supe " + supeId + " not found in database. No changes made.";
	    }
	    modelAndView.addObject("message", message);
	    modelAndView.setViewName("Supe/Output");

	    return modelAndView;
	}
}