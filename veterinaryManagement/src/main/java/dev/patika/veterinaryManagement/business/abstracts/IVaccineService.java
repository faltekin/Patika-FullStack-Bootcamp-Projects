package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Vaccine;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine, Long id);
    Vaccine update(Vaccine vaccine);
    boolean delete(Long id);
    Vaccine get(Long id);
    List<Vaccine> findAll();
    List<Vaccine> getVaccineByAnimalId(Long animalId);

    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);


}
