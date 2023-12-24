package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.mapper.VaccineMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;
    private final AnimalRepository animalRepository;


    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }

    public Vaccine getById(Long id){
        return this.vaccineRepository.findById(id).orElseThrow(() -> new RuntimeException(id + "Aşı bulunamadı."));
    }

    public VaccineResponse create(Long animal_id, VaccineRequest vaccineRequest) {
        Animal animal = animalRepository.findById(animal_id)
                .orElseThrow(() -> new RuntimeException("Hayvan bulunamadı."));

        Optional<Vaccine> existingVaccine = vaccineRepository.findByCodeAndNameAndAnimalAndProtectionFinishDateAfter(vaccineRequest.getCode(),vaccineRequest.getName(),vaccineRequest.getAnimal(),vaccineRequest.getProtectionStartDate());

        if (existingVaccine.isPresent()) {
            throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur !!!");
        }

        Vaccine vaccine = vaccineMapper.asEntity(vaccineRequest);
        vaccine.setAnimal(animal);

        Vaccine savedVaccine = vaccineRepository.save(vaccine);
        return vaccineMapper.asOutput(savedVaccine);
    }

    public void deleteById(Long id) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        if (vaccineFromDb.isPresent()) {
            vaccineRepository.delete(vaccineFromDb.get());
        } else {
            throw new RuntimeException(id + "id li aşı sistemde bulunamadı !!!");
        }
    }

    public List<Vaccine> findByAnimalId(Long id) {
        return vaccineRepository.findByAnimalId(id);
    }

    public VaccineResponse update(Long id, VaccineRequest request) {
        Optional<Vaccine> vaccineFromDb = vaccineRepository.findById(id);
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByCodeAndNameAndAnimalAndProtectionFinishDateAfter(request.getCode(),request.getName(),request.getAnimal(),request.getProtectionStartDate());

        if (vaccineFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız aşı sistemde bulunamadı. !!!.");
        }

        if (isVaccineExist.isPresent()) {
            throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur !!!");
        }

        Vaccine vaccine = vaccineFromDb.get();
        vaccineMapper.update(vaccine, request);
        return vaccineMapper.asOutput(vaccineRepository.save(vaccine));
    }

    public Optional<Vaccine> findByProtectionFinish(LocalDate startDate,LocalDate endDate){
        return vaccineRepository.findByProtectionFinishDateBetween(startDate,endDate);
    }
}
