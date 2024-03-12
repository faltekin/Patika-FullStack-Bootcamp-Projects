package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {

    List<Customer> findByNameContainingIgnoreCase(String name);
    Optional<Customer> findByMailAndPhone(String mail, String phone);


    Optional<Customer> findByMail(String mail);
    Optional<Customer> findByPhone(String phone);

}
