package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import dev.patika.VeterinaryManagementSystem.mapper.AnimalMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final AnimalMapper animalMapper;
    private final CustomerRepository customerRepository;

    public List<AnimalResponse> findAll() {
        return animalMapper.asOutput(animalRepository.findAll());
    }

    public AnimalResponse getById(Long id) {
        return animalMapper.asOutput(animalRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li Hayvan Bulunamadı !!!")));
    }


    public AnimalResponse update(Long id, AnimalRequest request) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);
        Optional<Animal> isAnimalExist = animalRepository.findByNameContainingIgnoreCase(request.getName());

        if (animalFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız hayvan sistemde bulunamadı. !!!.");
        }

        if (isAnimalExist.isPresent()) {
            throw new RuntimeException("Bu hayvan daha önce sisteme kayıt olmuştur !!!");
        }
        Animal animal = animalFromDb.get();
        animalMapper.update(animal, request);
        return animalMapper.asOutput(animalRepository.save(animal));
    }

    public void deleteById(Long id) {
        Optional<Animal> animalFromDb = animalRepository.findById(id);

        if (animalFromDb.isPresent()) {
            animalRepository.delete(animalFromDb.get());
        } else {
            throw new RuntimeException(id + " nolu hayvan bulunamadı.");
        }
    }
    public AnimalResponse save(Long customerId, AnimalRequest animalRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Müşteri bulunamadı."));

        Animal animal = animalMapper.asEntity(animalRequest);
        animal.setCustomer(customer);

        Animal savedAnimal = animalRepository.save(animal);
        return animalMapper.asOutput(savedAnimal);
    }
    public List<AnimalResponse> filter(String name) {
            Optional<Animal> animals = animalRepository.findByNameContainingIgnoreCase(name);
        return animals.stream()
                .map(animalMapper::asOutput)
                .collect(Collectors.toList());
    }
}

