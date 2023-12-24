package dev.patika.VeterinaryManagementSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @OneToMany (mappedBy = "customer", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animals;


}
