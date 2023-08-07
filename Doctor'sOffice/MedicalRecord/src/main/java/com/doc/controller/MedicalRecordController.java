package com.doc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.doc.dto.entity.MedicalRecord;
import com.doc.dto.entity.MedicalRecordList;
import com.doc.model.service.MedicalRecordService;

@RestController
@RequestMapping("/api/medicalrecords")
public class MedicalRecordController {

	@Autowired
	private MedicalRecordService medicalRecordService;
	
	@Autowired
    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

	@PostMapping("/medicalRecords")
    public ResponseEntity<MedicalRecord> createMedicalRecord(@RequestBody MedicalRecord medicalRecord) {
        MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(medicalRecord);
        if (createdMedicalRecord != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMedicalRecord);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
	
	@GetMapping("/{medicalRecordId}")
    public MedicalRecord getMedicalRecordById(@PathVariable int medicalRecordId) {
        return medicalRecordService.getMedicalRecordById(medicalRecordId);
    }

//    @PutMapping("/medicalRecords/{medicalRecordId}")
//    public ResponseEntity<Void> updateMedicalRecord(
//            @PathVariable int medicalRecordId, @RequestBody MedicalRecord updatedMedicalRecord) {
//        boolean result = medicalRecordService.updateMedicalRecord(medicalRecordId, updatedMedicalRecord);
//        if (result) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
	
	@PutMapping("/medicalRecords/{medicalRecordId}")
    public ResponseEntity<Void> updateMedicalRecord(
            @PathVariable int medicalRecordId, @RequestBody MedicalRecord updatedMedicalRecord) {
        boolean result = medicalRecordService.updateMedicalRecord(medicalRecordId, updatedMedicalRecord);
        if (result) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{medicalRecordId}")
    public MedicalRecord deleteMedicalRecord(@PathVariable int medicalRecordId) {
        return medicalRecordService.deleteMedicalRecord(medicalRecordId);
    }

    @GetMapping
    public List<MedicalRecord> getAllMedicalRecords() {
        return medicalRecordService.getAllMedicalRecords();
    }
	
}
