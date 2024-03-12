package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine,Long> {



    List<Vaccine> findByAnimalIdAndCodeAndName(Long animalId,String code,String name);
    List<Vaccine> findByAnimalId(Long animalId);

    List<Vaccine> findByAnimalIdAndName(Long animalId, String name);
    List<Vaccine> findAllByAnimalIdAndCodeAndProtectionFinishDateAfter(Integer animalId,String code,LocalDate startDate);

    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByAnimalIdAndProtectionFinishDate(Long animalId, LocalDate endDate);

    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);
    Optional<Vaccine> findByAnimalIdAndCode(Long animalId, String code);
    Optional<Vaccine> findByNameAndCodeAndAnimalId(String name,String code,Long animalId);
    Optional<Vaccine> findByAnimalIdAndProtectionStartDateAndProtectionFinishDate(Long animalId,LocalDate startDate, LocalDate endDate );

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<Vaccine> findByAnimalIdAndCodeAndProtectionStartDateAndProtectionFinishDate(Long animalId,String code,LocalDate startDate, LocalDate endDate );

    List<Vaccine> findAllByAnimalIdAndCodeAndNameAndProtectionFinishDateAfter(Integer animalId,String code,String name,LocalDate startDate);



}
