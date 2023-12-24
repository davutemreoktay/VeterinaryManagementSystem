package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/vaccine")
public class VaccineController {

    private final VaccineService vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll() {
        return vaccineService.findAll();

    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine getById(@PathVariable("id") Long id) {
        return vaccineService.getById(id);

    }
    @GetMapping("/filterWithAnimalId")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findByAnimalId(@RequestParam Long animal_id){
        return vaccineService.findByAnimalId(animal_id);
    }
    @GetMapping("/filterDate")
    public Optional<Vaccine>findByProtectionFinish(@RequestParam LocalDate startDate,@RequestParam LocalDate finishDate){
        return vaccineService.findByProtectionFinish(startDate,finishDate);
    }


    @PostMapping("/create/{animal_id}")
    public ResponseEntity<VaccineResponse> save(
            @PathVariable Long animal_id,
            @RequestBody VaccineRequest vaccineRequest
    ) {
        VaccineResponse savedVaccine = vaccineService.create(animal_id, vaccineRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVaccine);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        vaccineService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VaccineResponse> update(@PathVariable Long id, @RequestBody VaccineRequest request) {
        VaccineResponse updatedVaccine = vaccineService.update(id, request);
        return ResponseEntity.ok(updatedVaccine);
    }
}
