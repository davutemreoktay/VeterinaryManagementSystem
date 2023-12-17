package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.DoctorRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.DoctorResponse;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.mapper.DoctorMapper;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    public List<DoctorResponse> findAll() {

        return doctorMapper.asOutput(doctorRepository.findAll());
    }

    public DoctorResponse getById(Long id) {
        return doctorMapper.asOutput(doctorRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li Doktor Bulunamadı !!!")));
    }

    public DoctorResponse create(DoctorRequest request) {
        Optional<Doctor> isDoctorExist = doctorRepository.findByName(request.getName());

        if (isDoctorExist.isEmpty()) {
            Doctor doctorSaved = doctorRepository.save(doctorMapper.asEntity(request));
            return doctorMapper.asOutput(doctorSaved);
        }
        throw new RuntimeException("Bu doktor daha önce sisteme kayıt olmuştur !!!");
    }

    public DoctorResponse update(Long id, DoctorRequest request) {
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);
        Optional<Doctor> isDoctorExist = doctorRepository.findByName(request.getName());

        if (doctorFromDb.isEmpty()) {
            throw new RuntimeException(id + "Güncellemeye çalıştığınız doktor sistemde bulunamadı. !!!.");
        }

        if (isDoctorExist.isPresent()) {
            throw new RuntimeException("Bu doktor daha önce sisteme kayıt olmuştur !!!");
        }

        Doctor doctor = doctorFromDb.get();
        doctorMapper.update(doctor, request);
        return doctorMapper.asOutput(doctorRepository.save(doctor));
    }
    public void deleteById(Long id) {
        Optional<Doctor> doctorFromDb = doctorRepository.findById(id);

        if (doctorFromDb.isPresent()) {
            doctorRepository.delete(doctorFromDb.get());
        } else {
            throw new RuntimeException(id + " nolu doktor bulunamadı.");
        }
    }

}