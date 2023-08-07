package com.doc.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.doc.dto.entity.MedicalRecord;
import com.doc.dto.entity.Patient;
import com.doc.dto.entity.Prescription;
import com.doc.model.service.MedicalRecordService;
import com.doc.model.service.PrescriptionService;

@RestController
@RequestMapping("/medicalrecords")
@CrossOrigin
public class MedicalRecordController {

	@Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/")
    public ModelAndView medicalRecordIndex() {
        return new ModelAndView("MedicalRecord/MedicalRecordIndex");
    }

    @GetMapping("/inputMedicalRecordId")
    public ModelAndView inputMedicalRecordId() {
        return new ModelAndView("MedicalRecord/InputMedicalRecordId");
    }

    @GetMapping("/searchMedicalRecordById")
    public ModelAndView searchMedicalRecordById(@RequestParam("medicalRecordId") int medicalRecordId) {
        ModelAndView modelAndView = new ModelAndView();
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(medicalRecordId);
        if (medicalRecord != null) {
            modelAndView.addObject("medicalRecord", medicalRecord);
            modelAndView.setViewName("MedicalRecord/DisplayMedicalRecordById");
        } else {
            modelAndView.addObject("message", "Medical Record with ID " + medicalRecordId + " not found");
            modelAndView.setViewName("MedicalRecordOutput");
        }

        return modelAndView;
    }

    @GetMapping("/viewAllMedicalRecords")
    public ModelAndView viewAllMedicalRecords() {
        ModelAndView modelAndView = new ModelAndView();
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        modelAndView.addObject("medicalRecord", medicalRecords);
        modelAndView.setViewName("MedicalRecord/DisplayAllMedicalRecords");
        return modelAndView;
    }

    @RequestMapping("/inputMedicalRecordDetails")
    public ModelAndView inputMedicalRecordDetails() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("medicalRecord", new MedicalRecord());
        modelAndView.setViewName("MedicalRecord/CreateMedicalRecordEntry");
        return modelAndView;
    }

    @PostMapping("/saveMedicalRecord")
    public ModelAndView saveMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;

        if(medicalRecordService.createMedicalRecord(medicalRecord) != null)
            message = "Medical Record Added";
        else
            message = "Medical Record Not Added";

        modelAndView.addObject("message", message);
        modelAndView.setViewName("MedicalRecord/MedicalRecordOutput");

        return modelAndView;
    }

    @GetMapping("/inputMedicalRecordIdForDelete")
    public ModelAndView inputMedicalRecordIdForDelete() {
        ModelAndView modelAndView = new ModelAndView("MedicalRecord/InputMedicalRecordIdForDelete");
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        List<Integer> medicalRecordIds = medicalRecords.stream()
                .map(MedicalRecord::getMedicalRecordId)
                .collect(Collectors.toList());

        modelAndView.addObject("medicalRecordIds", medicalRecordIds);
        modelAndView.addObject("medicalRecord", new MedicalRecord());

        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteMedicalRecordById(@ModelAttribute("medicalRecord") MedicalRecord medicalRecord) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;
        int medicalRecordId = medicalRecord.getMedicalRecordId();

        if(medicalRecordService.deleteMedicalRecordById(medicalRecordId) != null) {
            message = "Medical Record with ID " + medicalRecordId + " deleted";
        } else {
            message = "Medical Record with ID " + medicalRecordId + " not deleted";
        }

        modelAndView.addObject("message", message);
        modelAndView.setViewName("MedicalRecord/MedicalRecordOutput");

        return modelAndView;
    }

    @GetMapping("/details/{medicalRecordId}")
    public ModelAndView viewMedicalRecordDetails(@PathVariable int medicalRecordId) {
        ModelAndView modelAndView = new ModelAndView("MedicalRecord/DisplayAllMedicalRecords");
        MedicalRecord medicalRecord = medicalRecordService.getMedicalRecordById(medicalRecordId);
        modelAndView.addObject("medicalRecord", medicalRecord);
        return modelAndView;
    }

    @GetMapping("/inputMedicalRecordDetailsForUpdate")
    public ModelAndView inputMedicalRecordDetailsForUpdate() {
        ModelAndView modelAndView = new ModelAndView("MedicalRecord/InputMedicalRecordDetailsForUpdate");
        List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
        List<Integer> medicalRecordIds = medicalRecords.stream()
                .map(MedicalRecord::getMedicalRecordId)
                .collect(Collectors.toList());

        modelAndView.addObject("medicalRecordIds", medicalRecordIds);
        modelAndView.addObject("MedicalRecord", new MedicalRecord());

        return modelAndView;
    }

    @PostMapping("/updateMedicalRecord")
    public ModelAndView updateMedicalRecord(@ModelAttribute("medicalRecord") MedicalRecord updatedMedicalRecord) {
        String message;
        int medicalRecordId = updatedMedicalRecord.getMedicalRecordId();
        int patientId = updatedMedicalRecord.getPatientId();
        String diagnoses = updatedMedicalRecord.getDiagnoses();
        String treatments = updatedMedicalRecord.getTreatments();
        String medications = updatedMedicalRecord.getMedications();

        MedicalRecord existingMedicalRecord = medicalRecordService.getMedicalRecordById(medicalRecordId);
        existingMedicalRecord.setDiagnoses(diagnoses);
        existingMedicalRecord.setTreatments(treatments);
        existingMedicalRecord.setMedications(medications);

        if(medicalRecordService.updateMedicalRecord(medicalRecordService.getMedicalRecordById(medicalRecordId)) != null)
            message = "Medical Record with ID " + medicalRecordId + " updated";
        else
            message = "Medical Record with ID " + medicalRecordId + " not updated";

        return new ModelAndView("MedicalRecord/MedicalRecordOutput", "message", message);
    }


}