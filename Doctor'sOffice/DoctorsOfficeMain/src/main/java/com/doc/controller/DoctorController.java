package com.doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.doc.dto.entity.Doctor;
import com.doc.model.service.DoctorService;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @ExceptionHandler(DoctorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleDoctorNotFoundException(HttpServletRequest request, DoctorNotFoundException ex) {
        return ex.getMessage();
    }
    
    @GetMapping("/doctorMainPage")
    public String doctorMainPage() {
        return "Doctor/DoctorMainPage";
    }

    @GetMapping("/displayDoctorById")
    public ModelAndView displayDoctorById(@RequestParam(value = "docId", required = false) Integer docId) {
        ModelAndView modelAndView = new ModelAndView();

        if (docId == null) {
            modelAndView.setViewName("Doctor/displayDoctorById");
        } else {
            List<Doctor> doctorList = doctorService.getDoctorById(docId);

            if (doctorList.isEmpty()) {
                modelAndView.setViewName("Doctor/Error");
            } else {
                modelAndView.addObject("doctorList", doctorList);
                modelAndView.setViewName("Doctor/displayDoctorDetailsPage");
            }
        }

        return modelAndView;
    }
    
    @GetMapping("/displayDoctorByName")
    public ModelAndView displayDoctorByName(@RequestParam(value = "docName", required = false) String docName) {
        ModelAndView modelAndView = new ModelAndView();

        if (docName == null) {
            modelAndView.setViewName("Doctor/displayDoctorByName");
        } else {
            List<Doctor> doctorList = doctorService.getDoctorByName(docName);

            if (doctorList.isEmpty()) {
                modelAndView.setViewName("Doctor/Error");
            } else {
                modelAndView.addObject("doctorList", doctorList);
                modelAndView.setViewName("Doctor/displayDoctorDetailsPage");
            }
        }

        return modelAndView;
    }
    
    @GetMapping("/displayAllDoctors")
    public ModelAndView displayAllDoctors() {
        ModelAndView modelAndView = new ModelAndView();
        
        List<Doctor> doctorList = doctorService.getAllDoctors();

        if (doctorList.isEmpty()) {
            modelAndView.setViewName("Doctor/Error");
        } else {
            modelAndView.addObject("doctorList", doctorList);
            modelAndView.setViewName("Doctor/displayAllDoctors");
        }

        return modelAndView;
    }

    @GetMapping("/updateDoctorEntryForm")
    public ModelAndView updateDoctorEntryForm() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        List<Integer> docIds = doctors.stream().map(Doctor::getDocId).collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("docIds", docIds);
        modelAndView.addObject("doctor", new Doctor());
        modelAndView.setViewName("Doctor/UpdateDoctorEntry");

        return modelAndView;
    }


    @PostMapping("/updateDoctorEntry")
    public ModelAndView updateDoctorEntry(@ModelAttribute("doctor") Doctor updatedDoctor) {
        Doctor doctor = doctorService.getDoctorById(updatedDoctor.getDocId()).stream().findFirst().orElse(null);

        if (doctor != null) {
            doctor.setDocName(updatedDoctor.getDocName());
            doctor.setDocQualification(updatedDoctor.getDocQualification());
            doctor.setDocSpecialty(updatedDoctor.getDocSpecialty());
            doctor.setDocGender(updatedDoctor.getDocGender());
            doctor.setDocDays(updatedDoctor.getDocDays());
            doctor.setDocHours(updatedDoctor.getDocHours());

            Doctor updatedDoctorObj = doctorService.updateDoctor(doctor);

            if (updatedDoctorObj != null) {
                ModelAndView modelAndView = new ModelAndView("redirect:/displayAllDoctors");
                modelAndView.addObject("successMessage", "Doctor entry updated successfully: " + updatedDoctorObj.getDocName());
                return modelAndView;
            }
        }

        return new ModelAndView("Doctor/Error").addObject("errorMessage", "Failed to update the doctor entry.");
    }


	@GetMapping("/deleteDoctorEntryForm")
	public ModelAndView deleteDoctorEntryForm() {
	    ModelAndView modelAndView = new ModelAndView("Doctor/DeleteDoctorEntry");

	    List<Doctor> doctors = doctorService.getAllDoctors();
	    List<Integer> validDoctorIds = doctors.stream()
	            .map(Doctor::getDocId)
	            .filter(Objects::nonNull)
	            .collect(Collectors.toCollection(ArrayList::new));

	    modelAndView.addObject("doctorIds", validDoctorIds);
	    modelAndView.addObject("doctor", new Doctor());

	    return modelAndView;
	}

	@DeleteMapping("/deleteDoctorEntry")
	public ModelAndView deleteDoctorEntry(@ModelAttribute("doctor") Doctor doctor) {
	    doctorService.deleteDoctor(doctor.getDocId());
	    return new ModelAndView("Doctor/deleteDoctorEntryOutput");
	}
    
    @GetMapping("/createDoctorEntryForm")
    public ModelAndView createDoctorEntryForm() {
        ModelAndView modelAndView = new ModelAndView("Doctor/CreateDoctorEntry");
        modelAndView.addObject("doctor", new Doctor());
        return modelAndView;
    }

    @PostMapping("/createDoctorEntry")
    public ModelAndView createDoctorEntry(@RequestBody Doctor doctor) {
        ModelAndView modelAndView;
        if (doctorService.addDoctor(doctor)) {
            modelAndView = new ModelAndView("Doctor/CreateDoctorEntry");
            modelAndView.addObject("doctor", doctor);
        } else {
            modelAndView = new ModelAndView("Doctor/Error");
            modelAndView.addObject("errorMessage", "Failed to add the doctor.");
        }
        return modelAndView;
    }

}