package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryManagementSystem.service.AvailableDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/availableDate")
public class AvailableDateController {

    private final AvailableDateService availableDateService;

    @Autowired
    public AvailableDateController(AvailableDateService availableDateService){
        this.availableDateService = availableDateService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDateResponse> findAll() {
        return availableDateService.findAll();
    }

    @PostMapping("/create/{doctorId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDateResponse save(@PathVariable Long doctorId, @RequestBody AvailableDateRequest availableDateRequest) {
        return availableDateService.create(doctorId, availableDateRequest);
    }

}
