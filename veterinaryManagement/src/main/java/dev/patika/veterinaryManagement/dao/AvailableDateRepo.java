package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AvailableDateRepo extends JpaRepository<AvailableDate,Long> {

    List<AvailableDate>  findByDoctorIdAndAvailableDate(Integer doctorId, LocalDate date);
    boolean existsByDoctorIdAndAvailableDate(Long doctorId, LocalDate date);



}
