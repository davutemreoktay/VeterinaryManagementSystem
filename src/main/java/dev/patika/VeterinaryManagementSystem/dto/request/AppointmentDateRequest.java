package dev.patika.VeterinaryManagementSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AppointmentDateRequest {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime appointmentDate;
    private Animal animal;
    private Doctor doctor;

}
