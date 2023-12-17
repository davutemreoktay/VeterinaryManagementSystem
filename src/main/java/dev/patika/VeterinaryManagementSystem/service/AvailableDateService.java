package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.AvailableDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AvailableDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.mapper.AvailableDateMapper;
import dev.patika.VeterinaryManagementSystem.repository.AvailableDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvailableDateService {

    private final AvailableDateRepository availableDateRepository;
    private final AvailableDateMapper availableDateMapper;
    private final DoctorRepository doctorRepository;
    public List<AvailableDateResponse> findAll() {
        return availableDateMapper.asOutput(availableDateRepository.findAll());
    }

    public AvailableDateResponse getById(Long id) {
        return availableDateMapper.asOutput(availableDateRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li müsait gün bulunamadı !!!")));
    }

    public AvailableDateResponse create(Long doctorId, AvailableDateRequest request) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException(doctorId + "id li doktor bulunamadı !!!"));

        AvailableDate availableDate = availableDateMapper.asEntity(request);
        availableDate.setDoctor(doctor);

        return availableDateMapper.asOutput(availableDateRepository.save(availableDate));
    }

}
