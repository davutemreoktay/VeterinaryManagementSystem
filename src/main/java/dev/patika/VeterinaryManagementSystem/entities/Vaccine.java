package dev.patika.VeterinaryManagementSystem.entities;


import dev.patika.VeterinaryManagementSystem.dto.response.VaccineResponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "vaccine")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vaccine {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "protection_start_date", nullable = false)
    private LocalDate protectionStartDate;

    @Column(name = "protection_finish_date", nullable = false)
    private LocalDate protectionFinishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id",referencedColumnName ="id")
    private Animal animal;


}
