package com.doc.model.service;

import com.doc.dto.entity.Prescription;
import com.doc.model.dao.PrescriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
	@Autowired
	private PrescriptionDao prescriptionDao;

	@Override
	public List<Prescription> getAllPrescriptions() {
		return prescriptionDao.findAll();
	}

	@Override
	public List<Prescription> getPrescriptionsByPatientId(int patientId) {
		return prescriptionDao.getPrescriptionByPatientId(patientId);
	}

	@Override
	public List<Prescription> getPrescriptionsByDoctorId(int doctorId) {
		return prescriptionDao.getPrescriptionByDoctorId(doctorId);
	}

	@Override
	public Prescription getPrescriptionById(int prescriptionId) {
		return prescriptionDao.findById(prescriptionId).orElse(null);
	}

	@Override
	public Prescription createPrescription(Prescription prescription) {
		return prescriptionDao.save(prescription);
	}

	@Override
	public Prescription updatePrescription(Prescription prescription) {
		Prescription existingPrescription = prescriptionDao.findById(prescription.getPrescriptionId()).orElse(null);

		if (existingPrescription != null) {
			existingPrescription.setDoctorId(prescription.getDoctorId());
			existingPrescription.setPatientId(prescription.getPatientId());
			existingPrescription.setMedicationName(prescription.getMedicationName());
			existingPrescription.setDosageInstructions(prescription.getDosageInstructions());
			existingPrescription.setDuration(prescription.getDuration());

			return prescriptionDao.save(existingPrescription);
		}
		return null;
	}

	@Override
	public Optional<Prescription> deletePrescriptionById(int prescriptionId) {
		Optional<Prescription> prescription = prescriptionDao.findById(prescriptionId);
		prescriptionDao.deleteById(prescriptionId);
		return prescription;
	}
}
