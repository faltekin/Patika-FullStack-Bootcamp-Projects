package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepo extends JpaRepository<Animal,Long> {

    // Kurallara uygun custom sorgu oluşturma
    Optional<Animal> findByName(String name);
    List<Animal> findByNameContainingIgnoreCase(String name);

    List<Animal> findByCustomerId(Long customerId);

    Optional<Animal> findByCustomerIdAndName(Long customerId, String name);
}
