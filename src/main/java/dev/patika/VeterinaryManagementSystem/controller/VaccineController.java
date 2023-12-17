package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService){
        this.vaccineService = vaccineService;
    }

    @GetMapping()
    public void findAll() {
        vaccineService.findAll();
    }

    @GetMapping("/{id}")
    public void getById(Long id) {
        vaccineService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody VaccineRequest vaccine) {vaccineService.create(vaccine);}

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        vaccineService.deleteById(id);
    }

    @PutMapping ("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VaccineResponse update(@PathVariable Long id, @RequestBody VaccineRequest request) {
        return vaccineService.update(id, request);
    }


}
