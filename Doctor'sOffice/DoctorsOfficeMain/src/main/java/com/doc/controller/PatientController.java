package com.doc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.doc.dto.entity.Patient;
import com.doc.model.service.PatientService;


@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @ExceptionHandler(PatientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePatientNotFoundException(HttpServletRequest request, PatientNotFoundException ex) {
        return ex.getMessage();
    }

    @GetMapping("/patientMainPage")
    public String patientMainPage() {
        return "Patient/PatientIndex";
    }

    @GetMapping("/displayPatientById")
    public ModelAndView displayPatientById(@RequestParam(value = "patientId", required = false) Integer patientId) {
        ModelAndView modelAndView = new ModelAndView();

        if (patientId == null) {
            modelAndView.setViewName("Patient/DisplayPatientById");
        } else {
        	 List<Patient> patientList = patientService.getPatientById(patientId);

             if (patientList.isEmpty()) {
                 modelAndView.setViewName("Patient/Error");
             } else {
                 modelAndView.addObject("patientList", patientList);
                 modelAndView.setViewName("Patient/ShowPatient");
             }
         }

         return modelAndView;
     }

    @GetMapping("/displayPatientByName")
    public ModelAndView displayPatientByName(@RequestParam(value = "patientName", required = false) String patientName) {
        ModelAndView modelAndView = new ModelAndView();

        if (patientName == null) {
            modelAndView.setViewName("Patient/DisplayPatientByName");
        } else {
            List<Patient> patients = patientService.getPatientsByName(patientName);

            if (patients.isEmpty()) {
                modelAndView.setViewName("Patient/Error");
            } else {
                modelAndView.addObject("patients", patients);
                modelAndView.setViewName("Patient/ShowPatient");
            }
        }

        return modelAndView;
    }

    @GetMapping("/displayAllPatients")
    public ModelAndView displayAllPatients() {
        ModelAndView modelAndView = new ModelAndView();

        List<Patient> patients = patientService.getAllPatients();

        if (patients.isEmpty()) {
            modelAndView.setViewName("Patient/Error");
        } else {
            modelAndView.addObject("patients", patients);
            modelAndView.setViewName("Patient/ShowPatient");
        }

        return modelAndView;
    }

    @GetMapping("/updatePatientEntryForm")
    public ModelAndView updatePatientEntryForm() {
        List<Patient> patients = patientService.getAllPatients();
        List<Integer> patientIds = patients.stream().map(Patient::getPatientId).collect(Collectors.toList());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("patientIds", patientIds);
        modelAndView.addObject("patient", new Patient());
        modelAndView.setViewName("Patient/UpdatePatientEntry");
        return modelAndView;
    }

    @PutMapping("/updatePatientEntry/{patientId}")
    public ModelAndView updatePatientEntry(@PathVariable int patientId, @ModelAttribute("patient") Patient patient) {
        Patient updatedPatient = patientService.updatePatientDetailsByPatientId(
                patientId,
                patient.getPatientName(),
                patient.getContactInfo(),
                patient.getDateOfBirth(),
                patient.getMedicalHistory()
        );

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("outputMessage", "Patient entry updated successfully: " + updatedPatient.getPatientName());
        modelAndView.setViewName("Patient/UpdatePatientEntryOutput");
        return modelAndView;
    }

    @GetMapping("/deletePatientEntryForm")
    public ModelAndView deletePatientEntryForm() {
        ModelAndView modelAndView = new ModelAndView();
        List<Patient> patientIds = patientService.getAllPatients();
        modelAndView.addObject("patientIds", patientIds);
        modelAndView.addObject("patient", new Patient());
        modelAndView.setViewName("Patient/DeletePatientEntry");
        return modelAndView;
    }

    @DeleteMapping("/deletePatientEntry")
    public String deletePatientEntry(@ModelAttribute("patient") Patient patient) {
        patientService.deletePatient(patient.getPatientId());
        return "Patient/DeletePatientEntryOutput";
    }

    @PostMapping(path = "/createPatientEntry")
    public ModelAndView createPatientEntry(@RequestBody Patient patient) {
        ModelAndView modelAndView;
        if (patientService.addPatient(patient)) {
            modelAndView = new ModelAndView("Patient/CreatePatientEntry");
            modelAndView.addObject("patient", patient);
        } else {
            modelAndView = new ModelAndView("Patient/Error");
            modelAndView.addObject("errorMessage", "Failed to add the patient.");
        }
        return modelAndView;
    }

    @GetMapping(path = "/createPatientEntryForm")
    public ModelAndView createPatientEntryForm() {
        ModelAndView modelAndView = new ModelAndView("Patient/CreatePatientEntry");
        modelAndView.addObject("patient", new Patient());
        return modelAndView;
    }
}
