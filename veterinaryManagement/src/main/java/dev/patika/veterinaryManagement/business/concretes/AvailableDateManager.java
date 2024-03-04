package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAvailableDateService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.AvailableDateRepo;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorDao doctorDao;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorDao doctorDao) {
        this.availableDateRepo = availableDateRepo;
        this.doctorDao = doctorDao;
    }


    @Override
    public AvailableDate save(AvailableDate availableDate,Long doctorId) {
        Doctor doctor = doctorDao.findById(doctorId).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        availableDate.setDoctor(doctor);

        return availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.availableDateRepo.delete(this.get(id));
        return true;
    }

    @Override
    public List<AvailableDate> findAll() {
        return this.availableDateRepo.findAll();
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {

        this.get(availableDate.getId());
        return this.availableDateRepo.save(availableDate);
    }

}
