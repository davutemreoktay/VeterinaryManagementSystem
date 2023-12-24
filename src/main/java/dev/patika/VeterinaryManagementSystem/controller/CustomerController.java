package dev.patika.VeterinaryManagementSystem.controller;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.CustomerRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomerResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.CustomersAnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customer")

public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getById(Long id) {
        return customerService.getById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerResponse save(@RequestBody CustomerRequest customer) {
        return customerService.create(customer);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse update(@PathVariable Long id, @RequestBody CustomerRequest request) {
        return customerService.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        customerService.deleteById(id);
    }

    @GetMapping("/filterWithName")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> filter(@RequestParam String name) {
        return customerService.filter(name);
    }
    //@RequestParam bir Spring MVC özelliğidir ve bir HTTP isteği sırasında gelen parametreleri kontrol etmek için kullanılır.
    //Özellikle, bir GET isteği ile gelen sorgu parametrelerini almak için kullanılır.
    //@RequestParam anotasyonu, belirli bir parametrenin adını ve değerini belirlemek için kullanılır.


    @GetMapping("/{customerId}/with-animals")
    @ResponseStatus(HttpStatus.OK)
    public CustomersAnimalResponse getCustomerWithAnimals(@PathVariable Long customerId) {
        return customerService.getCustomerWithAnimals(customerId);
    }
}