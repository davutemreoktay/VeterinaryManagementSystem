package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.AppointmentDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AppointmentDateRepository extends JpaRepository<AppointmentDate,Long> {

@Override
    Optional<AppointmentDate> findById(Long id);

    Optional<AppointmentDate> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime appointmentDate);

}

