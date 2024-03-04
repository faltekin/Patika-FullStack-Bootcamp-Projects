package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorDao doctorDao;

    public DoctorManager(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public Doctor save(Doctor doctor) {
        return this.doctorDao.save(doctor);
    }

    @Override
    public Doctor update(Doctor doctor) {

        this.get(doctor.getId());
        return this.doctorDao.save(doctor);
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.doctorDao.delete(this.get(id));
        return true;
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorDao.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Doctor> findAll() {
        return this.doctorDao.findAll();
    }
}
