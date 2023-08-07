package com.doc.doctor.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.doc.doctor.dto.entity.Doctor;
import com.doc.doctor.dto.entity.DoctorList;
import com.doc.doctor.service.DoctorService;

@RestController
@CrossOrigin
public class DoctorResource {

    @Autowired
    private DoctorService doctorService;

    @GetMapping(path = "/displayDoctorById/{docId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> displayDoctorById(@PathVariable("docId") int docId) {
    	Doctor doctor = doctorService.getDocByDocId(docId);
    	ResponseEntity response=null;
    	if (doctor != null)
			response=new ResponseEntity<Doctor>(doctor,HttpStatus.FOUND);
		else
			response=new ResponseEntity<Doctor>(doctor,HttpStatus.NOT_FOUND);
		return response;

    }

    @GetMapping(path = "/displayDoctorByName/{docName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> displayDoctorByName(@PathVariable("docName") String docName) {
    	Doctor doctor = doctorService.getDocByDocName(docName);
    	ResponseEntity response=null;
    	if (doctor != null)
			response=new ResponseEntity<Doctor>(doctor,HttpStatus.FOUND);
		else
			response=new ResponseEntity<Doctor>(doctor,HttpStatus.NOT_FOUND);
		return response;
    }

    @CrossOrigin
    @GetMapping(path = "/displayAllDoctors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorList> displayAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        DoctorList doctorList = new DoctorList(doctors);
        
        if (!doctorList.getDoctor().isEmpty()) {
            return new ResponseEntity<>(doctorList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

/*
    @PutMapping("/updateDoctorEntry")
    public ResponseEntity<Doctor> updateDoctorEntry(@RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.updateDocDetailsByDocId(
                doctor.getDocId(),
                doctor.getDocName(),
                doctor.getDocQualification(),
                doctor.getDocSpecialty(),
                doctor.getDocGender(),
                doctor.getDocDays(),
                doctor.getDocHours()
        );

        if (updatedDoctor != null) {
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

 */

    @PostMapping(path = "/updateDoctorEntry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> updateDoctorEntry(@RequestBody Doctor updatedDoctor) {
        Doctor doctor = doctorService.updateDocDetailsByDocId(updatedDoctor);
        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/deleteDoctorEntry/{docId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> deleteDoctorEntry(@PathVariable int docId) {
        Doctor doctor=doctorService.deleteDocByDocId(docId);
        if(doctor!=null)
            return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
        else
            return new ResponseEntity<Doctor>(HttpStatus.NO_CONTENT);
    }
     /*
    @DeleteMapping("/deleteDoctorEntry")
    public ResponseEntity<String> deleteDoctor(@PathVariable int docId) {
        boolean deleted = doctorService.deleteDocByDocId(docId);
        if (deleted) {
            return new ResponseEntity<>("Doctor with ID "+ docId + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Doctor with ID " + docId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

      */

    @PostMapping(path = "/createDoctorEntry", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> createDoctorEntry(@RequestBody Doctor doctor) {
		if(doctorService.addDoctor(doctor))
			return new ResponseEntity<Doctor>(doctor, HttpStatus.CREATED);
		else
			return new ResponseEntity<Doctor>(HttpStatus.NOT_ACCEPTABLE);
	}
}
