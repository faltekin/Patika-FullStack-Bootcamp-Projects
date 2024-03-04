package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.entities.Doctor;

import java.util.List;

public interface IDoctorService {

    Doctor save(Doctor doctor);
    Doctor update(Doctor doctor);
    boolean delete(Long id);
    Doctor get(Long id);
    List<Doctor> findAll();

}
