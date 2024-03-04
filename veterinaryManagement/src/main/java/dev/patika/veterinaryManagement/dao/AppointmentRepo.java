package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findByAnimalId(Long animalId);
    List<Appointment> findByDoctorId(Long doctorId);

    Appointment findByAppointmentDateAndDoctorId(LocalDateTime appointmentTime, Long doctorId);

    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId,LocalDateTime startDate,LocalDateTime endDate);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId,LocalDateTime startDate,LocalDateTime endDate);

    List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId);

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId,LocalDateTime appointmentDate);

}
