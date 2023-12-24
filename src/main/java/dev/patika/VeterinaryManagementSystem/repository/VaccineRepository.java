package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    List<Vaccine> findByAnimalId(Long animal_id);

    Optional<Vaccine> findByIdAndAnimalId(Long id, Long animal_id);

    Optional<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate,LocalDate endDate );

    Optional<Vaccine> findByCodeAndNameAndAnimalAndProtectionFinishDateAfter(String code, String name, Animal animal,LocalDate finishDate);

}
