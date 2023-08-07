package com.doc.client;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import com.doc.dto.entity.MedicalRecord;
import com.doc.model.dao.MedicalRecordDao;
import com.doc.model.service.MedicalRecordService;
import com.doc.model.service.MedicalRecordServiceImpl;

@SpringBootTest
class MedicalRecordApplicationTests {

	@Autowired
	private MedicalRecordService medicalRecordService;

	@Test
	@DisplayName("Find Medical Record By Id")
	public void findMedicalRecordByIdTest() {
		MedicalRecord expectedMedicalRecord = medicalRecordService.getMedicalRecordById(1);
		assertNotNull(expectedMedicalRecord);
		assertEquals(1, expectedMedicalRecord.getMedicalRecordId());
	}

	@Test
	@DisplayName("Get All Medical Records")
	public void getAllMedicalRecordsTest() {
		List<MedicalRecord> medicalRecord = medicalRecordService.getAllMedicalRecords();
		assertNotNull(medicalRecord);
		assertEquals(11, medicalRecord.size());
	}

	@Test
	@DisplayName("Create a Medical Record")
	void createMedicalRecordTest() {
		MedicalRecord testMedicalRecord = new MedicalRecord(11, 10, "Headache", "Rest", "Ibuprofen");

		MedicalRecord createdMedicalRecord = medicalRecordService.createMedicalRecord(testMedicalRecord);

		assertNotNull(createdMedicalRecord);
        assertNotNull(createdMedicalRecord.getMedicalRecordId());
		assertEquals(testMedicalRecord.getPatientId(), createdMedicalRecord.getPatientId());
		assertEquals(testMedicalRecord.getDiagnoses(), createdMedicalRecord.getDiagnoses());
		assertEquals(testMedicalRecord.getTreatments(), createdMedicalRecord.getTreatments());
		assertEquals(testMedicalRecord.getMedications(), createdMedicalRecord.getMedications());

	}
	
//	@Test
//	@DisplayName("Update a Medical Record")
//	void updateMedicalRecordTest() {
//
//		int medicalRecordId = 1;
//		MedicalRecord updatedMedicalRecord = new MedicalRecord();
//
//		MedicalRecordDao medicalRecordDao = mock(MedicalRecordDao.class);
//		when(medicalRecordDao.existsById(medicalRecordId)).thenReturn(true);
//
//		MedicalRecordService medicalRecordService = new MedicalRecordServiceImpl(medicalRecordDao);
//
//		boolean result = medicalRecordService.updateMedicalRecord(medicalRecordId, updatedMedicalRecord);
//
//		assertTrue(result);
//
//		verify(medicalRecordDao).existsById(medicalRecordId);
//		verify(medicalRecordDao).save(updatedMedicalRecord);
//	}

	@Test
	@DisplayName("Delete a MedicalRecord")
	public void deleteMedicalRecordTest() {

		medicalRecordService.deleteMedicalRecord(11);
		List<MedicalRecord> medicalRecords = medicalRecordService.getAllMedicalRecords();
		assertEquals(10, medicalRecords.size());
		MedicalRecord deletedMedicalRecords = medicalRecordService.getMedicalRecordById(11);

		assertNull(deletedMedicalRecords);
	}
}