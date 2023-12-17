package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.mapper.AnimalMapper;
import dev.patika.VeterinaryManagementSystem.service.AppointmentDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/appointmentDate")
public class AppointmentDateController {

    private final AppointmentDateService appointmentDateService;


    @Autowired
    public AppointmentDateController(AppointmentDateService appointmentDateService){
        this.appointmentDateService = appointmentDateService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AppointmentDateResponse> findAll() {
        return appointmentDateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AppointmentDateResponse getById(Long id) {
        return appointmentDateService.getById(id);
    }


    @PostMapping("/save-with-doctor-and-animal/{doctorId}/{animalId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AppointmentDateResponse saveWithDoctorAndAnimal(
            @PathVariable Long doctorId,
            @PathVariable Long animalId,
            @RequestBody AppointmentDateRequest appointmentDateRequest) {
        return appointmentDateService.save(doctorId, animalId, appointmentDateRequest);

    }


}
