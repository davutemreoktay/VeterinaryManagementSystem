package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.VaccineRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import dev.patika.VeterinaryManagementSystem.mapper.VaccineMapper;
import dev.patika.VeterinaryManagementSystem.repository.VaccineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {

    private final VaccineRepository vaccineRepository;
    private final VaccineMapper vaccineMapper;
    private final AnimalService animalService;

    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }

    public Vaccine getById(Long id) {
        return vaccineRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "id li Vaccine Bulunamadı !!!"));
    }

    public VaccineResponse create (VaccineRequest vaccine) {
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByCode(vaccine.getCode());

        if (isVaccineExist.isEmpty()) {
            Vaccine vaccineSaved = vaccineRepository.save(vaccineMapper.asEntity(vaccine));
            return vaccineMapper.asOutput(vaccineSaved);
        }
        throw new RuntimeException("Bu aşı daha önce sisteme kayıt olmuştur !!!");
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
        Optional<Vaccine> isVaccineExist = vaccineRepository.findByCode(request.getCode());

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



}
