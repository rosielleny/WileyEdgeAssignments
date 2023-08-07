package com.doc.model.service;

import java.util.Date;
import java.util.List;

import com.doc.dto.entity.Appointment;
import com.doc.dto.entity.composite.AppointmentPatientDoctor;

public interface AppointmentService {
	
	Appointment createAppointment(Appointment appointment);
    AppointmentPatientDoctor getAppointmentPDById(int appointmentId);
    Boolean deleteAppointment(int appointmentId);
	Boolean updateAppointment(int appointmentId, String field, String newValue);
	List<AppointmentPatientDoctor> searchByPatient(int patientId);
	List<AppointmentPatientDoctor> searchByDoctor(int doctorId);
	List<AppointmentPatientDoctor> searchByDate(String date);
	List<AppointmentPatientDoctor> getAllAppointments();
	boolean checkDoctorIsAvailable(int doctorId, Appointment appointment);
	boolean checkSlotIsAvailable(Appointment appointment);
	Appointment getAppointmentById(int appointmentId);
	boolean checkTimeIsFuture(Appointment appointment);

}
