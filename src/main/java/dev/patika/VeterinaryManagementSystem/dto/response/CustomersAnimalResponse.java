package dev.patika.VeterinaryManagementSystem.dto.response;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomersAnimalResponse {

    private Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<AnimalResponse> animalResponseList;


    public CustomersAnimalResponse(Long id, String name, String phone, String mail, String address, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.address = address;
        this.city = city;
    }

}