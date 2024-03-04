package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao extends JpaRepository<Customer,Long> {

    List<Customer> findByName(String name);
}
