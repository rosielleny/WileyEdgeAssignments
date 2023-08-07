package com.doc.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.doc.dto.entity.Appointment;
import com.doc.dto.entity.composite.AppointmentPatientDoctor;
import com.doc.model.service.AppointmentService;

@SpringBootTest
class DoctorsOfficeMainApplicationTests {

	@Autowired
	private AppointmentService appointmentService;

	@Test
	@DisplayName("Find Appointment By Id")
	public void findAppointmentByIdTest() {
		Appointment appointment = appointmentService.getAppointmentById(1);
		assertNotNull(appointment);
		assertEquals("1", appointment.getPatientId());
		assertEquals("1", appointment.getDoctorId());
		assertEquals("2023-07-20 10:00:00", appointment.getAppointmentTime());
		assertEquals("Confirmed", appointment.getAppointmentStatus());
		assertEquals("Follow-up checkup", appointment.getAdditionalNotes());
	}


	@Test
	@DisplayName("Get All Appointments")
	public void getAllAppointmentsTest() {
		List<AppointmentPatientDoctor> appointments = appointmentService.getAllAppointments();
		assertNotNull(appointments);
		assertEquals("10", appointments.size());
	}

	@Test
	@DisplayName("Create an Appointment")
	public void createAppointmentTest() {
		LocalDateTime appointmentTime = LocalDateTime.of(2023, 8, 6, 15, 30);
		Appointment testAppointment = new Appointment(11, 11, 11, appointmentTime, "Confirmed", "Test additional notes");

		appointmentService.createAppointment(testAppointment);
		Appointment createdAppointment = appointmentService.getAppointmentById(11);

		assertNotNull(createdAppointment);
		assertEquals(testAppointment.getPatientId(), createdAppointment.getPatientId());
		assertEquals(testAppointment.getDoctorId(), createdAppointment.getDoctorId());
		assertEquals(testAppointment.getAppointmentTime(), createdAppointment.getAppointmentTime());
		assertEquals(testAppointment.getAppointmentStatus(), createdAppointment.getAppointmentStatus());
		assertEquals(testAppointment.getAdditionalNotes(), createdAppointment.getAdditionalNotes());

	}

	@Test
	@DisplayName("Update an Appointment")
	public void updateAppointmentTest() {

		appointmentService.updateAppointment(11, "additionalNotes", "updated");
		Appointment updatedAppointment = appointmentService.getAppointmentById(11);

		assertEquals("updated", updatedAppointment.getAdditionalNotes());

	}

	@Test
	@DisplayName("Delete an Appointment")
	public void deleteAppointmentTest() {

		appointmentService.deleteAppointment(11);
		List<AppointmentPatientDoctor> appointments = appointmentService.getAllAppointments();
		assertEquals("10", appointments.size());
		Appointment deletedAppointment = appointmentService.getAppointmentById(11);

		assertNull(deletedAppointment);

	}

	@Test
	@DisplayName("Search by Patient")
	public void searchByPatientTest() {

		List<AppointmentPatientDoctor> appointments = appointmentService.searchByPatient(1);
		assertEquals("1", appointments.size());
	}

	@Test
	@DisplayName("Search by Doctor")
	public void searchByDoctorTest() {

		List<AppointmentPatientDoctor> appointments = appointmentService.searchByDoctor(1);
		assertEquals("1", appointments.size());
	}

	@Test
	@DisplayName("Search by Date")
	public void searchByDateTest() {

		List<AppointmentPatientDoctor> appointments = appointmentService.searchByDate("2023-07-20");
		assertEquals("1", appointments.size());
	}
}
