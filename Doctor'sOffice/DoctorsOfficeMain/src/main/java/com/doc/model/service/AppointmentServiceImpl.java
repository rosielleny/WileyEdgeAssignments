package com.doc.model.service;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.doc.dto.entity.Appointment;
import com.doc.dto.entity.Doctor;
import com.doc.dto.entity.Patient;
import com.doc.dto.entity.composite.AppointmentPatientDoctor;
import com.doc.model.dao.AppointmentDao;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Hard coding a Patient List for testing purposes while  Patient is under development //
	
	private List<Patient> hardCodedPatients = getHardCodedPatients();
//	private List<Doctor> hardCodedDoctors = getHardCodedDoctors();
	
	private List<Patient> getHardCodedPatients(){
		List<Patient> patients = new ArrayList<>(); 
		for(int i = 1; i < 11 ; i++) {
			LocalDate dob = LocalDate.of(1990, 1, 1).plusDays(i);
			Patient patient = new Patient(i, "patientName"+i, "contactInfo"+i, dob, "medicalHistory"+i);
			patients.add(patient); 
		}
		
		return patients;
	}
	
//	private List<Doctor> getHardCodedDoctors(){
//		
//		List<Doctor> doctors = new ArrayList<>(); //<-- Hard coding starts
//		for(int i = 1; i < 11 ; i++) {
//	
//			Doctor doctor = new Doctor(i, "docName"+i, "docQualification"+i, "docSpecialty"+i, "docGender"+i, "Monday, Tuesday, Wednesday, Thursday, Friday", "8:00 AM - 5:00 PM");
//			doctors.add(doctor); //<-- Hard coding ends
//		}
//		
//		return doctors;
//	}
	// Hard coding a Patient List for testing purposes while Patient is under development //
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final List<String> DAYS = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Appointment createAppointment(Appointment appointment) {
		
		if(appointmentDao.save(appointment) != null) {
			return appointment;
		} 
		else {
			return null;
		}
	}
	
	// Used to externally to get appointments in the form of the AppointmentPatientDoctor object
	@Override
	public AppointmentPatientDoctor getAppointmentPDById(int appointmentId) {
		
		Appointment appointment = appointmentDao.findById(appointmentId).orElse(null);
		if(appointment !=null) {
			return convertFromAppointmentToAPD(appointment);
		}
		else {
			return null;
		}
	}
	
	@Override
	public Appointment getAppointmentById(int appointmentId) {
		
		Appointment appointment = appointmentDao.findById(appointmentId).orElse(null);
		return appointment;
	}

	@Override
	public List<AppointmentPatientDoctor> getAllAppointments() {
		
		return gatherAppointmentInformation(appointmentDao.findAll());
		
	}

	@Override
	public Boolean deleteAppointment(int appointmentId) {
		
		Appointment appointment = getAppointmentById(appointmentId);
		if(appointment !=null) {
			appointmentDao.deleteById(appointmentId);
			return true;
		}
		return false;
	}

	@Override
	public Boolean updateAppointment(int appointmentId, String field, String newValue) {
		
		Appointment appointment = getAppointmentById(appointmentId);
		if(appointment != null) {
			switch(field) {
			case "patient":
				int parsedPatientId = Integer.parseInt(newValue);
				appointment.setPatientId(parsedPatientId);
				break;
			case "doctor":
				int parsedDoctorId = Integer.parseInt(newValue);
				appointment.setDoctorId(parsedDoctorId);
				break;
			case "date":
				//LocalDateTime parsedAppointmentTime = LocalDateTime.parse(newValue, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
				appointment.setAppointmentTime(newValue);
				break;
			case "status":
				appointment.setAppointmentStatus(newValue);
				break;
			case "notes":
				appointment.setAdditionalNotes(newValue);
				break;
			default:
				return false;
			}
			
			appointmentDao.save(appointment);
			return true;
		}
		return false;
	}
	
	@Override
	public List<AppointmentPatientDoctor> searchByPatient(int patientId){
		List<Appointment> appointments = appointmentDao.findByPatient(patientId);
		return gatherAppointmentInformation(appointments);
	}
	
	@Override
	public List<AppointmentPatientDoctor> searchByDoctor(int doctorId){
		List<Appointment> appointments = appointmentDao.findByDoctor(doctorId);
		return gatherAppointmentInformation(appointments);
	}
	
	@Override
	public List<AppointmentPatientDoctor> searchByDate(String date){
		
		List<Appointment> appointments = appointmentDao.findByDate(date);
		return gatherAppointmentInformation(appointments);
	}
	
	
	private List<AppointmentPatientDoctor> gatherAppointmentInformation(List<Appointment> appointments){

		List<AppointmentPatientDoctor> apptInfoList = new ArrayList<>();

		for(Appointment appointment: appointments) {

			AppointmentPatientDoctor apptInfo = convertFromAppointmentToAPD(appointment);

			apptInfoList.add(apptInfo);
		}

		return apptInfoList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Temporarily hard coding results for this function while Doctor and Patient are under development //
	private AppointmentPatientDoctor convertFromAppointmentToAPD(Appointment appointment) {
		
		int doctorId = appointment.getDoctorId();
		int patientId = appointment.getPatientId();
		
		// TODO Check this matches Patient and Doctor once they are complete
		//Patient patient = restTemplate.getForObject("http://localhost:4040/patient/" + patientId, Patient.class);
		Doctor doctor = restTemplate.getForObject("http://localhost:8090/displayDoctorById/" + doctorId, Doctor.class);
		 
		

		AppointmentPatientDoctor apptInfo = new AppointmentPatientDoctor();
		apptInfo.setAppointmentId(appointment.getAppointmentId());
		apptInfo.setAppointmentTime(appointment.getAppointmentTime());
		apptInfo.setAppointmentStatus(appointment.getAppointmentStatus());
		apptInfo.setAdditionalNotes(appointment.getAdditionalNotes());
		apptInfo.setDoctorName(appointment.getDoctorId() + " | " + doctor.getDocName());
		apptInfo.setPatientName(appointment.getPatientId() + " | " + "Test Patient Name"); // < Hard coding here
		
		return apptInfo;
		
	}
	// Temporarily hard coding results for this function while Doctor and Patient are under development //
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// Methods for checking the input appointment time against the doctor's availability
	
	/* First section checks the appointment time against the doctors given
	 * availability schedule
	 * Second section checks the appointment time and doctor ID against existing appointments */
	
	// Time vs Availability Schedule
	
	
	@Override
	public boolean checkDoctorIsAvailable(int doctorId, Appointment appointment) {
		
		Doctor theDoctor = restTemplate.getForObject("http://localhost:8090/displayDoctorById/" + doctorId, Doctor.class);
		
//		Doctor theDoctor = null; // <-- Hard coding starts here
//		for(Doctor doctor: hardCodedDoctors) {
//		
//			if(doctor.getDocId() == doctorId) {
//				
//				theDoctor = doctor;
//				break;
//			}
//		} // <-- Hard coding ends here
				
	    String availability = theDoctor.getDocDays(); // Retrieve the availability days
	    String timeRange = theDoctor.getDocHours(); // Retrieve the availability hours
	   
	    List<String> availableDays = new ArrayList<>(Arrays.asList(availability.split(", ")));
	   
	    System.out.println(checkTimeInRange(appointment.getAppointmentTime(), timeRange));
	    return checkDayInRange(appointment.getAppointmentTime(), availableDays)
	            && checkTimeInRange(appointment.getAppointmentTime(), timeRange);
		

	}
	

	
	private boolean checkDayInRange(LocalDateTime appointmentTime, List<String> availableDays) {

		for(String day : availableDays) {
			// Checking the string for the day against the list of days to get its index, comparing that to the appointment time's day as a number
			if(DAYS.indexOf(day)  == appointmentTime.getDayOfWeek().getValue() - 1) {
			
				return true; // Returning true if the integers match
			}
		}
		return false;
	}
	
	private boolean checkTimeInRange(LocalDateTime appointmentTime, String availableTimes) {
		
		// Splitting the availableTimes string on the '-' to extract start and end times
		
	    String[] times = availableTimes.split("-");
	    
	    // Defining a DateTimeFormatter to parse time strings like "8:00 AM" or "2:00 PM".
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

	    
	    // Parsing the start and end times using the formatter.
	    LocalTime startTime = LocalTime.parse(times[0].trim().toLowerCase(), formatter);
	    LocalTime endTime = LocalTime.parse(times[1].trim().toLowerCase(), formatter);
	    System.out.print("Start:" + startTime);
	    System.out.print("End:" + endTime);
	    
		return !appointmentTime.toLocalTime().isBefore(startTime) && !appointmentTime.toLocalTime().isAfter(endTime);
	}
	
	
	// Time and DocID vs existing appointments
	

	@Override
	public boolean checkSlotIsAvailable(Appointment appointment) {
		
		Doctor doctor = restTemplate.getForObject("http://localhost:8090/displayDoctorById/" + appointment.getDoctorId(), Doctor.class);
		
//		Doctor theDoctor = null; // <-- Hard coding starts here
//		for(Doctor doctor: hardCodedDoctors) {
//			
//			if(doctor.getDocId() == appointment.getDoctorId()) {
//				
//				theDoctor = doctor;
//				break;
//			}
//		} // <-- Hard coding ends here
		
		LocalDateTime appointmentTime = appointment.getAppointmentTime();
		java.time.LocalDate appointmentDate = appointmentTime.toLocalDate();
		
		String date = appointmentDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		List<AppointmentPatientDoctor> appointmentsOnDay = searchByDate(date);
		
		for(AppointmentPatientDoctor apd : appointmentsOnDay) {
			
			String doctorInfo = apd.getDoctorName(); 
		    int doctorIdForApd = Integer.parseInt(doctorInfo.split("\\|")[0].trim());

		    if((apd.getAppointmentTime()).equals(appointment.getAppointmentTime()) && doctorIdForApd == appointment.getDoctorId()) {
		    	return false;
		    }

		}
	
		return true;
		
	}
	
	@Override
	public boolean checkTimeIsFuture(Appointment appointment) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
	    return appointment.getAppointmentTime().isAfter(currentDateTime);
	}

}

