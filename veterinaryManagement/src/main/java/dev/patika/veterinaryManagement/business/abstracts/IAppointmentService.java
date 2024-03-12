package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {



    AppointmentResponse save(AppointmentSaveRequest appointmentSaveRequest);
    Appointment get(Long id);

    AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest);
    boolean delete(Long id);
    //List<Appointment> findAll();

    ResultData<List<AppointmentResponse>> findAll();



    ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime);
    ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime);



    List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId);





    ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetweenWithoutHour(Long doctorId, LocalDate startDate, LocalDate endDate);

    ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetweenWithoutHour(Long animalId, LocalDate startDate, LocalDate endDate);




}
