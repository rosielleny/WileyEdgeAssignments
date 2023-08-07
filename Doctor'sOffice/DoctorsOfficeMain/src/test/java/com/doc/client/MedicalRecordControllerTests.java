//package com.doc.client;
//
//import com.doc.controller.MedicalRecordController;
//import com.doc.dto.entity.MedicalRecord;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//class MedicalRecordControllerTests {
//
//    private MedicalRecordController medicalRecordController;
//    private RestTemplate restTemplate;
//
//    private final String medicalRecordServiceUrl = "http://localhost:5051/medical-record";
//
//    @BeforeEach
//    void setUp() {
//        restTemplate = mock(RestTemplate.class);
//        medicalRecordController = new MedicalRecordController();
//    }
//
//    @Test
//    void testMedicalRecordMainPage() {
//        List<MedicalRecord> sampleMedicalRecords = Arrays.asList(
//                new MedicalRecord(1, 101, "Common cold", "Rest, fluids", "Acetaminophen"),
//                new MedicalRecord(2, 102, "Hypertension", "Prescription medication", "Lisinopril")
//        );
//
//        ResponseEntity<List<MedicalRecord>> responseEntity = new ResponseEntity<>(sampleMedicalRecords, HttpStatus.OK);
//        when(restTemplate.exchange(
//                ArgumentMatchers.eq(medicalRecordServiceUrl),
//                ArgumentMatchers.eq(HttpMethod.GET),
//                ArgumentMatchers.isNull(),
//                ArgumentMatchers.<Class<List<MedicalRecord>>>any()))
//                .thenReturn(responseEntity);
//
//        ModelAndView modelAndView = medicalRecordController.medicalRecordMainPage();
//
//        assertEquals("MedicalRecord/MedicalRecordIndex", modelAndView.getViewName());
//
//        List<MedicalRecord> medicalRecords = (List<MedicalRecord>) modelAndView.getModel().get("medicalRecords");
//        assertEquals(sampleMedicalRecords, medicalRecords);
//
//        verify(restTemplate, times(1)).exchange(
//                ArgumentMatchers.eq(medicalRecordServiceUrl),
//                ArgumentMatchers.eq(HttpMethod.GET),
//                ArgumentMatchers.isNull(),
//                ArgumentMatchers.<Class<List<MedicalRecord>>>any());
//    }
//}