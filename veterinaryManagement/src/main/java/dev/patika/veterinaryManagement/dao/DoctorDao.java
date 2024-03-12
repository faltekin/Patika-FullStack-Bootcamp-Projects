package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {

    Optional<Doctor> findByMail(String mail);
    Optional<Doctor> findByPhone(String phone);
}
