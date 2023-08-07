CREATE SCHEMA DoctorsOfficeTest;
USE DoctorsOfficeTest;

CREATE TABLE Appointment (
  appointment_id INT PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  appointment_time DATETIME,
  appointment_status VARCHAR(20),
  additional_notes VARCHAR(200));
  
  INSERT INTO Appointment (appointment_id, patient_id, doctor_id, appointment_time, appointment_status, additional_notes)
VALUES
  (1, 1, 1, '2023-07-20 10:00:00', 'Confirmed', 'Follow-up checkup'),
  (2, 2, 2, '2023-07-22 09:30:00', 'Confirmed', 'Vaccination'),
  (3, 3, 3, '2023-07-23 14:00:00', 'Confirmed', ''),
  (4, 4, 4, '2023-07-25 11:30:00', 'Pending', ''),
  (5, 5, 5, '2023-07-26 08:45:00', 'Confirmed', 'X-ray appointment'),
  (6, 6, 6, '2023-07-27 10:30:00', 'Confirmed', 'Annual checkup'),
  (7, 7, 7, '2023-07-30 13:15:00', 'Confirmed', ''),
  (8, 8, 8, '2023-07-31 16:45:00', 'Pending', ''),
  (9, 9, 9, '2023-08-02 09:00:00', 'Confirmed', 'UTI symptoms'),
  (10, 10, 10, '2023-08-04 11:30:00', 'Confirmed', '');