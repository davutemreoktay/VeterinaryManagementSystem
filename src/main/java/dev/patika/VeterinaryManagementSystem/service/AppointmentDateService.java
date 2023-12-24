package dev.patika.VeterinaryManagementSystem.service;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.request.AppointmentDateRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.dto.response.AppointmentDateResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import dev.patika.VeterinaryManagementSystem.entities.AvailableDate;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import dev.patika.VeterinaryManagementSystem.mapper.AppointmentDateMapper;
import dev.patika.VeterinaryManagementSystem.repository.AnimalRepository;
import dev.patika.VeterinaryManagementSystem.repository.AppointmentDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.AvailableDateRepository;
import dev.patika.VeterinaryManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AppointmentDateService {

    private final AppointmentDateRepository appointmentDateRepository;
    private final AppointmentDateMapper appointmentDateMapper;
    private final DoctorRepository doctorRepository;
    private final AnimalRepository animalRepository;

    public List<AppointmentDateResponse> findAll() {
        return appointmentDateMapper.asOutput(appointmentDateRepository.findAll());
    }
    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndDoctorId(LocalDateTime startDate, LocalDateTime endDate, Long doctorId) {
        List<AppointmentDate> filteredAppointments = appointmentDateRepository
                .findByAppointmentDateBetweenAndDoctorId(startDate, endDate, doctorId);

        return appointmentDateMapper.asOutput(filteredAppointments);
    }

    public List<AppointmentDateResponse> getAppointmentsByDateRangeAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId) {
        List<AppointmentDate> filteredAppointments = appointmentDateRepository
                .findByAppointmentDateBetweenAndAnimalId(startDate, endDate, animalId);

        return appointmentDateMapper.asOutput(filteredAppointments);
    }


    public AppointmentDateResponse getById(Long id) {
        return appointmentDateMapper.asOutput(appointmentDateRepository.findById(id).orElseThrow(()
                -> new RuntimeException(id + "id li Randevu Bulunamadı !!!")));
    }
    public AppointmentDateResponse save(Long doctorId,Long animalId, AppointmentDateRequest request) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new RuntimeException("Bu id no ile hayvan bulunamadı."));
        LocalDateTime requestedDate = request.getAppointmentDate();

        // Doktorun o gün başka bir randevusu var mı kontrolü
        Optional<AppointmentDate> existingAppointment = appointmentDateRepository
                .findByDoctorIdAndAppointmentDate(doctorId, LocalDateTime.from(requestedDate));

        if (existingAppointment.isPresent()) {
            throw new RuntimeException("Doktorun bu gün başka bir randevusu bulunmaktadır.");
        }


        // Doktorun müsait günü var mı kontrolü (burada doktorun müsait gün kontrolü yapılabilir)
        // Örneğin, doctorRepository üzerinden doktorun müsait gün bilgisini alabilir ve kontrol edebilirsiniz.

        // Doktorun müsait günü varsa yeni randevu oluşturulabilir
        AppointmentDate appointmentDate = appointmentDateMapper.asEntity(request);
        appointmentDate.setDoctor(doctorRepository.getById(doctorId));
        appointmentDate.setAnimal(animal);


        return appointmentDateMapper.asOutput(appointmentDateRepository.save(appointmentDate));
    }



}
