package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VaccineDao extends JpaRepository<Vaccine,Long> {

    List<Vaccine> findByAnimalId(Long animalId);

    List<Vaccine> findAllByAnimalIdAndCodeAndProtectionFinishDateAfter(Integer animalId,String code,LocalDate startDate);

    List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate);

    List<Vaccine> findByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate);

}
