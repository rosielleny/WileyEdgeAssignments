package com.doc.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.doc.dto.entity.Appointment;
import com.doc.dto.entity.Doctor;
import com.doc.dto.entity.Patient;
import com.doc.dto.entity.composite.AppointmentPatientDoctor;
import com.doc.model.service.AppointmentService;
import com.doc.model.service.DoctorService;
import com.doc.model.service.PatientService;


@Controller
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
    
	// Main appointment page

	@RequestMapping("/appointment-main")
	public ModelAndView AppointmentMainPage() {
		return new ModelAndView("Appointment/AppointmentIndex");
	}
	
	// ModelAttributes
	
	@ModelAttribute("appointmentIds")
	public List<Integer> getAppointmentIds(){
		
		List<Integer> appointments = appointmentService.getAllAppointments()
				.stream()
				.map(AppointmentPatientDoctor::getAppointmentId) 
				.collect(Collectors.toList()); 
		return appointments;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
// Temporarily hard coding results for this function while Doctor and Patient are under development //
	@ModelAttribute("patientIds")
	public List<String> getPatientIds() {
	    //List<Patient> patients = patientService.getAllPatients();
		
		List<Patient> patients = new ArrayList<>(); //<-- Hard coding starts
		for(int i = 1; i < 11 ; i++) {
			LocalDate dob = LocalDate.of(1990, 1, 1).plusDays(i);
			Patient patient = new Patient(i, "patientName"+i, "contactInfo"+i, dob, "medicalHistory"+i);
			patients.add(patient); //<-- Hard coding ends
		}
	    return patients.stream()
	            .map(patient -> patient.getPatientId() + " - " + patient.getPatientName())
	            .collect(Collectors.toList());
	}

	// Temporarily hard coding results for this function while Doctor and Patient are under development //
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@ModelAttribute("doctorIds")
	public List<String> getDoctorIds() {
	    List<Doctor> doctors = doctorService.getAllDoctors();
		
//		List<Doctor> doctors = new ArrayList<>(); //<-- Hard coding starts
//		for(int i = 1; i < 11 ; i++) {
//	
//			Doctor doctor = new Doctor(i, "docName"+i, "docQualification"+i, "docSpecialty"+i, "docGender"+i, "Monday, Wednesday, Friday", "8:00 AM - 4:00 PM");
//			doctors.add(doctor); //<-- Hard coding ends
//		}
		
	    return doctors.stream()
	            .map(doctor -> doctor.getDocId() + " - " + doctor.getDocName())
	            .collect(Collectors.toList());
	}
	

	// Read

	@RequestMapping("/appointment-main/InputAppointmentSearchInfo")
	public ModelAndView InputAppointmentSearchInfo() {
		return new ModelAndView("Appointment/InputAppointmentSearchInfo");
	}
	
	@RequestMapping("/appointment-main/searchAppointment")
	public ModelAndView searchAppointment(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String field = request.getParameter("field");
		List<AppointmentPatientDoctor> appointmentInfo = new ArrayList<>();
		
		if(field != null) {
			if(field.equals("patient")) {
				appointmentInfo = appointmentService.searchByPatient(Integer.parseInt(request.getParameter("patientId")));
			}
			else if(field.equals("date")) {
				appointmentInfo = appointmentService.searchByDate(request.getParameter("date"));
			}
			else if(field.equals("doctor")) {
				appointmentInfo = appointmentService.searchByDoctor(Integer.parseInt(request.getParameter("doctorId")));
			}
			else if(field.equals("id")) {
				
				AppointmentPatientDoctor apd = appointmentService.getAppointmentPDById(Integer.parseInt(request.getParameter("appointmentID")));
	            if(apd != null) {
	                appointmentInfo.add(apd);
	            }
			}
			else if(field.equals("all")) {
				appointmentInfo = appointmentService.getAllAppointments();
			}
			else{
				modelAndView.addObject("message", "No appointments found.");
				modelAndView.setViewName("Appointment/Output");
			}
		}


		if(appointmentInfo!=null && appointmentInfo.size() >= 1) {
			modelAndView.addObject("appointmentInfo", appointmentInfo);
			modelAndView.setViewName("Appointment/ShowAppointment");
		}
		else {
			modelAndView.addObject("message", "No appointments found.");
			modelAndView.setViewName("Appointment/Output");
		}

		return modelAndView;
	}
	
	// Appointment Deletion

	
	@RequestMapping("/appointment-main/InputAppointmentIdForDelete")
	public ModelAndView InputAppointmentIdForDelete() {
		return new ModelAndView("Appointment/InputAppointmentIdForDelete", "appointment", new Appointment());
	}

	@RequestMapping("/appointment-main/deleteAppointment")
	public ModelAndView deleteAppointment(@ModelAttribute("appointment") Appointment appointment) {
		ModelAndView modelAndView = new ModelAndView();
		String message = null;
		int appointmentId = appointment.getAppointmentId();
		if(appointmentService.deleteAppointment(appointmentId) != null) {
			message = "Appointment" + appointmentId + " deleted.";
		}else {
			message = "Appointment" + appointmentId + " not found in database. No changes made.";
		}
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Appointment/Output");

		return modelAndView;
	}
	
	// Appointment create
	@RequestMapping("/appointment-main/InputAppointmentDetails")
	public ModelAndView InputAppointmentDetailsPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("appointment", new Appointment());
		modelAndView.setViewName("Appointment/InputAppointmentDetails");
		return modelAndView;
		
	}
	@RequestMapping("/saveAppointment")
	public ModelAndView saveAppointment(@Valid @ModelAttribute("appointment")Appointment appointment, BindingResult results) {
		
		
		ModelAndView modelAndView = new ModelAndView();
		if(results.hasErrors()) {
			return new ModelAndView("Appointment/InputAppointmentDetails", "appointment", appointment);
		}
		
		String message = null;
		
		if(!appointmentService.checkDoctorIsAvailable(appointment.getDoctorId(), appointment)) {
		    message = "Doctor is not available during selected hours. Appointment not added.";
		} else if(!appointmentService.checkSlotIsAvailable(appointment)) {
		    message = "Doctor has a pre-existing appointment at selected time. Appointment not added.";
		}else if(!appointmentService.checkTimeIsFuture(appointment)) {
			message = "Appointments cannot be added for past dates and times. Appointment not added.";
		} else if(appointmentService.createAppointment(appointment) != null) {
		    message = "Appointment Added.";
		} else {
		    message = "Appointment not Added.";
		}
		
		modelAndView.addObject("message", message);
		modelAndView.setViewName("Appointment/Output");
		
		return modelAndView;
	}
	
	@RequestMapping("/appointment-main/InputForAppointmentUpdate")
	public ModelAndView InputAppointmentDetailsForUpdateController() {
		
		ModelAndView modelAndView = new ModelAndView("Appointment/InputAppointmentDetailsForUpdate");
	    modelAndView.addObject("appointmentIds", getAppointmentIds());
	    return modelAndView;
	}


	@RequestMapping("/appointment-main/updateAppointment")
	public ModelAndView updateAppointment(HttpServletRequest request) {

		String message = null;
		int appointmentId = Integer.parseInt(request.getParameter("appointmentID"));
		String field = request.getParameter("field");
		String newValue = null;
		Boolean validDate = true;

		if (field.equals("patient")) {

			newValue = request.getParameter("patientId");

		} else if (field.equals("date")) {

			newValue = request.getParameter("appointmentTime");
			Appointment appointment = appointmentService.getAppointmentById(appointmentId);
			if(appointment !=null) {
				appointment.setAppointmentTime(newValue);
				if(!appointmentService.checkDoctorIsAvailable(appointment.getDoctorId(), appointment)) {
					message = "Doctor is not available during selected hours. Appointment not updated.";
					validDate = false;
				} else if(!appointmentService.checkSlotIsAvailable(appointment)) {
					message = "Doctor has a pre-existing appointment at selected time. Appointment not updated.";
					validDate = false;
				}else if(!appointmentService.checkTimeIsFuture(appointment)) {
					message = "Appointments cannot be added for past dates and times. Appointment not updated.";
					validDate = false;
				}
			}
		} else if (field.equals("doctor")) {

			newValue = request.getParameter("doctorId");
		}
		else if (field.equals("notes")) {
			
			newValue = request.getParameter("additionalNotes");
		}
		else if (field.equals("status")) {
			newValue = request.getParameter("appointmentStatus");
		}


		if(validDate) {
			if(appointmentService.updateAppointment(appointmentId, field, newValue)) {
				switch(field) {
				case "patient":
					field = "Patient";
					break;
				case "doctor":
					field = "Doctor";
					break;
				case "date":
					field = "Date";
					break;
				case "status":
					field = "Appointment Status";
					break;
				case "notes":
					field = "Additional Notes";
					break;
				}
				message = field + " updated for Appointment " +appointmentId+ ".";
			}else {
				message = "No changes made.";
			}
		}	
		return new ModelAndView("Appointment/Output", "message", message);
	}


}
