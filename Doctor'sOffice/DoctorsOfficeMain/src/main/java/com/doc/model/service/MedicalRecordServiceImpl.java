package com.doc.model.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.doc.dto.entity.MedicalRecord;
import com.doc.dto.entity.Prescription;
import com.doc.model.dao.MedicalRecordDao;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

	@Autowired
	private MedicalRecordDao medicalRecordDao;


	@Override
	public List<MedicalRecord> getAllMedicalRecords() {
		return medicalRecordDao.findAll();
	}

	@Override
	public MedicalRecord getMedicalRecordById(int medicalRecordId) {
		return medicalRecordDao.findById(medicalRecordId).orElse(null);
	}

	@Override
	public MedicalRecord createMedicalRecord(MedicalRecord medicalRecord) {
		return medicalRecordDao.save(medicalRecord);
	}

	@Override
	public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
		MedicalRecord existingMedicalRecord = medicalRecordDao.findById(medicalRecord.getMedicalRecordId()).orElse(null);

		if (existingMedicalRecord != null) {
			existingMedicalRecord.setMedicalRecordId(medicalRecord.getMedicalRecordId());
			existingMedicalRecord.setPatientId(medicalRecord.getPatientId());
			existingMedicalRecord.setDiagnoses(medicalRecord.getDiagnoses());
			existingMedicalRecord.setTreatments(medicalRecord.getTreatments());
			existingMedicalRecord.setMedications(medicalRecord.getMedications());

			return medicalRecordDao.save(existingMedicalRecord);
		}
		return null;
	}

	@Override
	public Optional<MedicalRecord> deleteMedicalRecordById(int medicalRecordId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
	
}
