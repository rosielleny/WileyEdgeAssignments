package com.doc.doctor.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doc.doctor.dto.entity.Doctor;

@Transactional
@Repository
public interface DoctorDao extends JpaRepository<Doctor, Integer> {
	
	@Query ("FROM Doctor WHERE docId = :docId")
	Doctor getDocByDocId (@Param ("docId") int docId);
	
	@Query ("FROM Doctor WHERE docName = :docName")
    Doctor getDocByDocName (@Param ("docName") String docName);

    @Query ("FROM Doctor")
    List<Doctor> getAllDoctors();

    /*
    @Modifying
	@Query ("UPDATE Doctor SET docName = :docName, docQualification = :docQualification, docSpecialty = :docSpecialty, docGender = :docGender, docDays = :docDays, docHours = :docHours WHERE docId = :docId" )
	Doctor updateDocDetailsByDocId (
			@Param ("docName") String docName, 
			@Param ("docQualification") String docQualification,
			@Param ("docSpecialty") String docSpecialty,
			@Param ("docGender") String docGender,
			@Param ("docDays") String docDays,
			@Param ("docHours") String docHours);

     */

	@Modifying
	@Query ("DELETE FROM Doctor WHERE docId = :docId")
	Doctor deleteDocByDocId (@Param ("docId") int docId);
    
    @Modifying
	@Query (value = "INSERT INTO Doctor VALUES (?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
	int addDoctor (int docId, String docName, String docQualification, String docSpecialty, String docGender, String docDays, String docHours);
	
}
