package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {

    Appointment save(Appointment appointment);
    Appointment get(Long id);
    Appointment update(Appointment appointment);
    boolean delete(Long id);
    List<Appointment> findAll();
    List<Appointment> getAppointmentByDoctorId(Long doctorId);
    List<Appointment> getAppointmentByAnimalId(Long animalId);
    List<Appointment> getAppointmentsByDateRange(String startDate, String endDate);

    List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId);

    List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime);



}
