package com.doc.doctor.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.doctor.dto.entity.Doctor;
import com.doc.doctor.persistence.DoctorDao;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorDao doctorDao;
	
	@Override
	public Doctor getDocByDocId (int docId) {
		return doctorDao.getDocByDocId (docId);
	}

	@Override
	public Doctor getDocByDocName (String DocName) {
		return doctorDao.getDocByDocName (DocName);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.getAllDoctors();
	}

	@Override
	public Doctor updateDocDetailsByDocId(Doctor doctor) {
		return doctorDao.save(doctor);
	}
	/*
	@Override
    @Transactional
    public Doctor updateDocDetailsByDocId(
            int docId,
            String docName,
            String docQualification,
            String docSpecialty,
            String docGender,
            String docDays,
            String docHours) {

        Optional<Doctor> optionalDoctor = doctorDao.findById(docId);

        if (optionalDoctor.isPresent()) {
            Doctor doctorToUpdate = optionalDoctor.get();
            doctorToUpdate.setDocName(docName);
            doctorToUpdate.setDocQualification(docQualification);
            doctorToUpdate.setDocSpecialty(docSpecialty);
            doctorToUpdate.setDocGender(docGender);
            doctorToUpdate.setDocDays(docDays);
            doctorToUpdate.setDocHours(docHours);
            return doctorDao.save(doctorToUpdate);
        } else {
       
            return null;
        }
    }

	 */

	@Override
	@Transactional
	public Doctor deleteDocByDocId(int docId) {
		Optional<Doctor> optionalDoctor = doctorDao.findById(docId);
		if (optionalDoctor.isPresent()) {
			Doctor doctorToDelete = optionalDoctor.get();
			doctorDao.deleteById(docId);
			return doctorToDelete;
		} else {
			return null;
		}
	}



	/*
	@Override
	public boolean deleteDocByDocId(int docId) {
		try {
			doctorDao.deleteById(docId);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	 */
	
	@Override
	public boolean addDoctor(Doctor doctor) {
	    try {
	        int rowsAffected = doctorDao.addDoctor(
	            doctor.getDocId(),
	            doctor.getDocName(),
	            doctor.getDocQualification(),
	            doctor.getDocSpecialty(),
	            doctor.getDocGender(),
	            doctor.getDocDays(),
	            doctor.getDocHours()
	        );
	        
	        return rowsAffected == 1;
	    } catch (Exception ex) {
	        return false;
	    }
	}

}
