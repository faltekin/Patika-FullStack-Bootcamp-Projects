package dev.patika.veterinaryManagement.dao;

import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDao extends JpaRepository<Doctor,Long> {


}
