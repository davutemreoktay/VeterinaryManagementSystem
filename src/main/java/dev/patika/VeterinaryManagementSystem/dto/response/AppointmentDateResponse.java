package dev.patika.VeterinaryManagementSystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Doctor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDateResponse {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime appointmentDate;


}
