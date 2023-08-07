package com.doc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@CrossOrigin
public class DoctorsOfficeController {
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("Main/Home");
    }
}
