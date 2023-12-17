package dev.patika.VeterinaryManagementSystem.mapper;

import dev.patika.VeterinaryManagementSystem.dto.request.AnimalRequest;
import dev.patika.VeterinaryManagementSystem.dto.response.AnimalResponse;
import dev.patika.VeterinaryManagementSystem.entities.Animal;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
@Mapper
public interface AnimalMapper {

    Animal asEntity(AnimalRequest animalRequest);
    //AnimalMapper arayüzünde, asEntity metodu AnimalRequest nesnesini Animal nesnesine dönüştürmek için kullanılır.
    //Bu dönüşüm işlemi, AuthorRequest nesnesindeki verileri alıp, yeni bir Animal nesnesi oluşturarak gerçekleştirir.

    AnimalResponse asOutput(Animal animal);
    //asOutput metodu ise Author nesnesini AnimalResponse nesnesine dönüştürür. Bu dönüşüm işlemi, Animal nesnesindeki verileri alıp, yeni bir AnimalResponse nesnesi oluşturarak gerçekleştirir.
    //AnimalResponse nesnesi, API tarafından kullanıcıya döndürülecek verileri içerir.

    List<AnimalResponse> asOutput(List<Animal> animal);

    void update(@MappingTarget Animal entity, AnimalRequest request);
    //update metodu, mevcut bir Animal nesnesinin verilerini güncellemek için kullanılır.
    //Bu yöntem, bir AnimalRequest nesnesini ve güncellenmek istenen Animal nesnesini parametre olarak alır.


}
