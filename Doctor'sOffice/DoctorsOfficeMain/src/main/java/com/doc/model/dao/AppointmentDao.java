package com.doc.model.dao;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doc.dto.entity.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer>{
	
	@Query("FROM Appointment WHERE patientId = :patientId")
    List<Appointment> findByPatient(@Param("patientId") int patientId);
    
    @Query("FROM Appointment WHERE doctorId = :doctorId")
    List<Appointment> findByDoctor(@Param("doctorId") int doctorId);
    
    @Query("SELECT a FROM Appointment a WHERE FUNCTION('DATE', a.appointmentTime) LIKE %?1%")
    List<Appointment> findByDate(String date);

}
