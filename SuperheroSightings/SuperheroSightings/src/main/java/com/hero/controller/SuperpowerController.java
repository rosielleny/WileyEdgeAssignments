package com.hero.controller;

import com.hero.dto.entity.Supe;
import com.hero.model.service.SupeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class SuperpowerController {

    @Autowired
    private SupeService supeService;
    
//	Main index page for superpowers
	@RequestMapping("/superPowerDetails")
	public ModelAndView superPowerDetails() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("Superpower/SuperpowerIndex");
	    return modelAndView;
	}

	@RequestMapping("/addSuperpowerForm")
    public ModelAndView addSuperpowerForm() {
        ModelAndView modelAndView = new ModelAndView("Superpower/CreateSuperpower");
        modelAndView.addObject("supe", new Supe());
        return modelAndView;
    }

    @RequestMapping("/addSuperpower")
    public ModelAndView addSuperpower(@ModelAttribute("supe") Supe supe) {
        ModelAndView modelAndView = new ModelAndView();
        String message;
        Supe createdSupe = supeService.createSupe(supe);
        if (createdSupe != null) {
            message = "Superpower added";
        } else {
            message = "Superpower Not Added";
        }

        modelAndView.addObject("message", message);
        modelAndView.setViewName("Supe/Output");
        return modelAndView;
    }
    
    @RequestMapping("/ViewSuperpower")
    public ModelAndView displaySuperpowers() {
        List<Supe> supes = supeService.getAllSupes();
        ModelAndView modelAndView = new ModelAndView("Superpower/ViewSuperpowers");
        modelAndView.addObject("supes", supes);
        return modelAndView;
    }

    //Update superpower for supe
    @PutMapping("/UpdateSuperpower")
    public ModelAndView updateSuperpower(@PathVariable int supeId, @RequestParam String superpower) {
        Supe supe = supeService.getSupeById(supeId);
        if (supe != null) {
            supe.setSupePower(superpower);
            supe = supeService.updateSupe(supeId, supe.getSupeName());
            if (supe != null) {
                return new ModelAndView("redirect:/ViewSuperpowers");
            }
        }
        return new ModelAndView("redirect:/ViewSuperpowers");
    }

    
    //Delete superpower for supe
    @DeleteMapping("/DeleteSuperpower")
    public ModelAndView deleteSuperpower(@PathVariable int supeId) {
        Supe supe = supeService.getSupeById(supeId);
        if (supe != null) {
            supe.setSupePower(null);
            supe = supeService.updateSupe(supeId, supe.getSupeName());
            if (supe != null) {
                return new ModelAndView("redirect:/ViewSuperpowers");
            }
        }
        return new ModelAndView("redirect:/ErrorPage");
    }

}
