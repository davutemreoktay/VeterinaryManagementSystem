package dev.patika.VeterinaryManagementSystem.repository;

import dev.patika.VeterinaryManagementSystem.entities.Animal;
import dev.patika.VeterinaryManagementSystem.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Optional<Customer> findByName (String name);

    Optional<Customer> findByNameContainingIgnoreCase (String name);



}
