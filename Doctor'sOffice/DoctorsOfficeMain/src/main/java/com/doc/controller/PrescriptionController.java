package com.doc.controller;

import com.doc.dto.entity.Prescription;
import com.doc.model.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prescriptions")
@CrossOrigin
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/")
    public ModelAndView prescriptionIndex() {
        return new ModelAndView("Prescription/Index");
    }

    @GetMapping("/inputPrescriptionId")
    public ModelAndView inputPrescriptionId() {
        return new ModelAndView("Prescription/InputPrescriptionId");
    }

    @GetMapping("/inputPatientId")
    public ModelAndView inputPatientId() {
        return new ModelAndView("Prescription/InputPatientId");
    }

    @GetMapping("/inputDoctorId")
    public ModelAndView inputDoctorId() {
        return new ModelAndView("Prescription/InputDoctorId");
    }

    @GetMapping("/searchPrescriptionById")
    public ModelAndView searchPrescriptionById(@RequestParam("prescriptionId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Prescription prescription = prescriptionService.getPrescriptionById(id);
        if (prescription != null) {
            modelAndView.addObject("prescription", prescription);
            modelAndView.setViewName("Prescription/PrescriptionDetails");
        } else {
            modelAndView.addObject("message", "Prescription with ID " + id + " not found");
            modelAndView.setViewName("Output");
        }

        return modelAndView;
    }

    @GetMapping("/searchPrescriptionByPatientId")
    public ModelAndView searchPrescriptionByPatientId(@RequestParam("patientId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByPatientId(id);
        if (prescriptions != null) {
            modelAndView.addObject("prescriptions", prescriptions);
            modelAndView.setViewName("Prescription/ViewPrescriptions");
        } else {
            modelAndView.addObject("message", "Prescriptions for patient with ID " + id + " not found");
            modelAndView.setViewName("Output");
        }

        return modelAndView;
    }

    @GetMapping("/searchPrescriptionByDoctorId")
    public ModelAndView searchPrescriptionByDoctorId(@RequestParam("doctorId") int id) {
        ModelAndView modelAndView = new ModelAndView();
        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByDoctorId(id);
        if (prescriptions != null) {
            modelAndView.addObject("prescriptions", prescriptions);
            modelAndView.setViewName("Prescription/ViewPrescriptions");
        } else {
            modelAndView.addObject("message", "Prescriptions for patient with ID " + id + " not found");
            modelAndView.setViewName("Output");
        }

        return modelAndView;
    }

    @GetMapping("/viewAll")
    public ModelAndView viewAllPrescriptions() {
        ModelAndView modelAndView = new ModelAndView();
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        modelAndView.addObject("prescriptions", prescriptions);
        modelAndView.setViewName("Prescription/ViewPrescriptions");
        return modelAndView;
    }

    //TODO need to add drop-down menus to add doctorid and patientid from db after doctor and patient are connected
    @RequestMapping("/inputPrescriptionDetails")
    public ModelAndView inputPrescriptionDetails() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("prescription", new Prescription());
        modelAndView.setViewName("Prescription/CreatePrescription");
        return modelAndView;
    }

    @PostMapping("/savePrescription")
    public ModelAndView savePrescription(@ModelAttribute("prescription") Prescription prescription) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;

        if(prescriptionService.createPrescription(prescription) != null)
            message = "Prescription Added";
        else
            message = "Prescription Not Added";

        modelAndView.addObject("message", message);
        modelAndView.setViewName("Prescription/Output");

        return modelAndView;
    }

    @GetMapping("/inputPrescriptionIdForDelete")
    public ModelAndView inputPrescriptionIdForDelete() {
        ModelAndView modelAndView = new ModelAndView("Prescription/InputPrescriptionIdForDelete");
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        List<Integer> prescriptionIds = prescriptions.stream()
                .map(Prescription::getPrescriptionId)
                .collect(Collectors.toList());

        modelAndView.addObject("prescriptionIds", prescriptionIds);
        modelAndView.addObject("prescription", new Prescription());

        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView deletePrescriptionById(@ModelAttribute("prescription") Prescription prescription) {
        ModelAndView modelAndView = new ModelAndView();
        String message = null;
        int id = prescription.getPrescriptionId();

        if(prescriptionService.deletePrescriptionById(id) != null) {
            message = "Prescription with ID " + id + " deleted";
        } else {
            message = "Prescription with ID " + id + " not deleted";
        }

        modelAndView.addObject("message", message);
        modelAndView.setViewName("Prescription/Output");

        return modelAndView;
    }

    @GetMapping("/details/{prescriptionId}")
    public ModelAndView viewPrescriptionDetails(@PathVariable int prescriptionId) {
        ModelAndView modelAndView = new ModelAndView("Prescription/PrescriptionDetails");
        Prescription prescription = prescriptionService.getPrescriptionById(prescriptionId);
        modelAndView.addObject("prescription", prescription);
        return modelAndView;
    }

    @GetMapping("/inputPrescriptionDetailsForUpdate")
    public ModelAndView inputPrescriptionDetailsForUpdate() {
        ModelAndView modelAndView = new ModelAndView("Prescription/UpdatePrescription");
        List<Prescription> prescriptions = prescriptionService.getAllPrescriptions();
        List<Integer> prescriptionIds = prescriptions.stream()
                .map(Prescription::getPrescriptionId)
                .collect(Collectors.toList());

        modelAndView.addObject("prescriptionIds", prescriptionIds);
        modelAndView.addObject("prescription", new Prescription());

        return modelAndView;
    }

    @PostMapping("/updatePrescription")
    public ModelAndView updatePrescription(@ModelAttribute("prescription") Prescription updatedPrescription) {
        String message;
        int prescriptionId = updatedPrescription.getPrescriptionId();
        String medicationName = updatedPrescription.getMedicationName();
        String dosageInstructions = updatedPrescription.getDosageInstructions();
        String duration = updatedPrescription.getDuration();

        Prescription existingPrescription = prescriptionService.getPrescriptionById(prescriptionId);
        existingPrescription.setMedicationName(medicationName);
        existingPrescription.setDosageInstructions(dosageInstructions);
        existingPrescription.setDuration(duration);

        if(prescriptionService.updatePrescription(prescriptionService.getPrescriptionById(prescriptionId)) != null)
            message = "Prescription with ID " + prescriptionId + " updated";
        else
            message = "Prescription with ID " + prescriptionId + " not updated";

        return new ModelAndView("Prescription/Output", "message", message);
    }


}
