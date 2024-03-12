package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {

    Optional<AvailableDate> findByDoctorIdAndAvailableDate(Long doctorId, LocalDate date);
    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate date);



}
