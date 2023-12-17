package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal,Long> {

    Optional<Animal> findByNameContainingIgnoreCase(String name);
    // Veritabanından name'e göre Animal nesnesini ara
    // Eğer bulunursa, Optional.of ile bir Optional içinde Animal nesnesini döndür
    // Bulunamazsa, Optional.empty() ile boş bir Optional döndür
    // Bu sayede null kontrollerini kullanıcıya bırakarak daha temiz bir kod elde edilir
    // Örneğin, Optional kullanmadan bu metodun null değer döndürmesi durumunda
    // çağıran tarafta null kontrolü yapmak gerekecekti.



}
