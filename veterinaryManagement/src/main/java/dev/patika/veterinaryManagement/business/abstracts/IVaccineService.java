package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Vaccine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IVaccineService {


    VaccineResponse save(VaccineSaveRequest vaccineSaveRequest);


    VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest);


    ResultData<List<VaccineResponse>> findByAnimalIdAndProtectionStartDateBetween2(Long animalId, LocalDate startDateTime, LocalDate endDateTime);




    boolean delete(Long id);
    Vaccine get(Long id);
    ResultData<List<VaccineResponse>> findAll();




    //List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);


    ResultData<List<VaccineResponse>> findByProtectionFinishDateBetween(LocalDate startDateTime, LocalDate endDateTime);

    ResultData<List<VaccineResponse>> findByAnimalId(Long animalId);

}
