CREATE SCHEMA DoctorsOfficeTest;
USE DoctorsOfficeTest;

DROP TABLE IF EXISTS Patient;
DROP TABLE IF EXISTS Doctor;
DROP TABLE IF EXISTS Appointment;
DROP TABLE IF EXISTS MedicalRecord;
DROP TABLE IF EXISTS Prescription;

CREATE TABLE Patient (
  patient_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_name VARCHAR(50),
  contact_info VARCHAR(100),
  date_of_birth DATE,
  medical_history VARCHAR(200));

CREATE TABLE Doctor (
  doctor_id INT PRIMARY KEY AUTO_INCREMENT,
  doctor_name VARCHAR(50),
  contact_details VARCHAR(100),
  specialization VARCHAR(50),
  availability_schedule VARCHAR(100));

CREATE TABLE Appointment (
  appointment_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT,
  doctor_id INT,
  appointment_time DATETIME,
  appointment_status VARCHAR(20),
  additional_notes VARCHAR(200),
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
  FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id));

CREATE TABLE MedicalRecord (
  medical_record_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT,
  diagnoses VARCHAR(200),
  treatments VARCHAR(200),
  medications VARCHAR(200),
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id));

CREATE TABLE Prescription (
  prescription_id INT PRIMARY KEY AUTO_INCREMENT,
  patient_id INT,
  doctor_id INT,
  medication_name VARCHAR(50),
  dosage_instructions VARCHAR(200),
  duration VARCHAR(20),
  FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
  FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id));

INSERT INTO Patient (patient_name, contact_info, date_of_birth, medical_history)
VALUES
  ('John Doe', 'john.doe@example.com', '1990-05-15', 'Allergies: None'),
  ('Jane Smith', 'jane.smith@example.com', '1985-10-10', 'Allergies: Penicillin'),
  ('Michael Johnson', 'michael.johnson@example.com', '1978-08-25', 'Allergies: None'),
  ('Emily Davis', 'emily.davis@example.com', '1992-03-12', 'Allergies: Shellfish'),
  ('Sarah Wilson', 'sarah.wilson@example.com', '1980-12-03', 'Allergies: None'),
  ('David Thompson', 'david.thompson@example.com', '1995-06-20', 'Allergies: None'),
  ('Jennifer Brown', 'jennifer.brown@example.com', '1975-09-18', 'Allergies: Peanuts'),
  ('Robert Harris', 'robert.harris@example.com', '1988-02-07', 'Allergies: None'),
  ('Amy Lee', 'amy.lee@example.com', '1993-11-30', 'Allergies: None'),
  ('Daniel Miller', 'daniel.miller@example.com', '1983-04-09', 'Allergies: Latex');

INSERT INTO Doctor (doctor_name, contact_details, specialization, availability_schedule)
VALUES
  ('Dr. Robert Johnson', 'robert.johnson@example.com', 'Cardiology', 'Mon-Fri, 9AM-5PM'),
  ('Dr. Emily Davis', 'emily.davis@example.com', 'Pediatrics', 'Mon, Wed, Fri, 8AM-12PM'),
  ('Dr. Michael Anderson', 'michael.anderson@example.com', 'Dermatology', 'Tue, Thu, Sat, 10AM-4PM'),
  ('Dr. Lisa Wilson', 'lisa.wilson@example.com', 'Family Medicine', 'Mon-Fri, 9AM-6PM'),
  ('Dr. James Thompson', 'james.thompson@example.com', 'Orthopedics', 'Tue, Thu, Sat, 8AM-2PM'),
  ('Dr. Jennifer Brown', 'jennifer.brown@example.com', 'Obstetrics and Gynecology', 'Mon, Wed, Fri, 9AM-1PM'),
  ('Dr. Daniel Miller', 'daniel.miller@example.com', 'Ophthalmology', 'Mon-Fri, 10AM-5PM'),
  ('Dr. Sarah Wilson', 'sarah.wilson@example.com', 'Psychiatry', 'Tue, Thu, Sat, 12PM-6PM'),
  ('Dr. David Thompson', 'david.thompson@example.com', 'Urology', 'Mon-Fri, 8AM-4PM'),
  ('Dr. Jessica Harris', 'jessica.harris@example.com', 'Endocrinology', 'Mon, Wed, Fri, 9AM-3PM');

INSERT INTO Appointment (patient_id, doctor_id, appointment_time, appointment_status, additional_notes)
VALUES
  (1, 1, '2023-07-20 10:00:00', 'Confirmed', 'Follow-up checkup'),
  (2, 2, '2023-07-22 09:30:00', 'Confirmed', 'Vaccination'),
  (3, 3, '2023-07-23 14:00:00', 'Confirmed', ''),
  (4, 4, '2023-07-25 11:30:00', 'Pending', ''),
  (5, 5, '2023-07-26 08:45:00', 'Confirmed', 'X-ray appointment'),
  (6, 6, '2023-07-27 10:30:00', 'Confirmed', 'Annual checkup'),
  (7, 7, '2023-07-30 13:15:00', 'Confirmed', ''),
  (8, 8, '2023-07-31 16:45:00', 'Pending', ''),
  (9, 9, '2023-08-02 09:00:00', 'Confirmed', 'UTI symptoms'),
  (10, 10, '2023-08-04 11:30:00', 'Confirmed', '');

INSERT INTO MedicalRecord (patient_id, diagnoses, treatments, medications)
VALUES
  (1, 'Hypertension', 'Prescribed medication and lifestyle changes', 'Medication A, Medication B'),
  (2, 'Common cold', 'Bed rest and fluids', 'Medication C'),
  (3, 'Eczema', 'Topical ointment and allergy management', 'Medication D'),
  (4, 'Sprained ankle', 'RICE method and pain management', 'Medication E'),
  (5, 'Sinusitis', 'Antibiotics and nasal spray', 'Medication F, Medication G'),
  (6, 'Annual checkup', 'General examination and blood tests', ''),
  (7, 'Cataracts', 'Surgical intervention required', ''),
  (8, 'Anxiety disorder', 'Therapy and medication', 'Medication H'),
  (9, 'Urinary tract infection', 'Prescribed antibiotics', 'Medication I'),
  (10, 'Diabetes', 'Insulin injections and dietary management', 'Insulin, Medication J');

INSERT INTO Prescription (patient_id, doctor_id, medication_name, dosage_instructions, duration)
VALUES
  (1, 1, 'Medication A', 'Take one tablet daily after breakfast', '1 month'),
  (2, 2, 'Medication C', 'Take two tablets every 6 hours', '10 days'),
  (3, 3, 'Medication D', 'Apply to affected areas twice daily', '2 weeks'),
  (4, 4, 'Medication E', 'Take as needed for pain relief', 'N/A'),
  (5, 5, 'Medication F', 'Take one tablet every 12 hours', '1 week'),
  (6, 6, 'Medication G', 'Use nasal spray as directed', '2 weeks'),
  (7, 7, 'Medication H', 'Take one tablet daily in the morning', '1 month'),
  (8, 8, 'Medication H', 'Take as needed for anxiety symptoms', 'N/A'),
  (9, 9, 'Medication I', 'Take one tablet twice daily', '10 days'),
  (10, 10, 'Insulin', 'Inject as directed before meals', 'Indefinite');

