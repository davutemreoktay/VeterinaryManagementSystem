package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/animal")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired//Bir sınıfın bir başka sınıfı veya bileşeni otomatik olarak enjekte etmesini sağlar.
    public AnimalController(AnimalService animalService){
        this.animalService = animalService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> findAll() {
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse getById(Long id) {
        return animalService.getById(id);
    }

    @PostMapping("/save/{customer_id}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnimalResponse save(
            @PathVariable Long customer_id,
            @RequestBody AnimalRequest animalRequest
    ) {
        return animalService.save(customer_id, animalRequest);
    }

    @PutMapping("//{id}")
    @ResponseStatus(HttpStatus.OK)
    public AnimalResponse update(@PathVariable Long id, @RequestBody AnimalRequest request) {
        return animalService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        animalService.deleteById(id);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<AnimalResponse> getAnimalsByName(@RequestParam String name) {
        return animalService.filter(name);
    }

}
