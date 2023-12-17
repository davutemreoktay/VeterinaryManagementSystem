package dev.patika.VeterinaryManagementSystem.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime appointmentDate;

}
