package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAppointmentService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.AppointmentRepo;
import dev.patika.veterinaryManagement.dao.AvailableDateRepo;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AvailableDateRepo availableDateRepo;
    private final DoctorDao doctorDao;

    public AppointmentManager(AppointmentRepo appointmentRepo, AvailableDateRepo availableDateRepo, DoctorDao doctorDao) {
        this.appointmentRepo = appointmentRepo;
        this.availableDateRepo = availableDateRepo;
        this.doctorDao = doctorDao;
    }


    @Override
    public Appointment save(Appointment appointment) {

        LocalDateTime appointmentTime = appointment.getAppointmentDate();
        Long doctorId = appointment.getDoctor().getId();

        if(isAvailableDateExist(doctorId, appointmentTime.toLocalDate())) {     // Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_DAY);
        }
        if(isAppointmentExist(doctorId, appointmentTime)) {                     // Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_APPOINTMENT);
        } else {
            return this.appointmentRepo.save(appointment);
        }

    }

    private boolean isAvailableDateExist(Long doctorId, LocalDate date) {
        return !availableDateRepo.existsByDoctorIdAndAvailableDate(doctorId,date); // Date bulunamadıysa false olur  -> ! ile true yaparız -> if bloğunda hata yazdırılır
    }

    private boolean isAppointmentExist(Long doctorId, LocalDateTime dateTime) {
        return appointmentRepo.existsByDoctorIdAndAppointmentDate(doctorId, dateTime); // Date varsa hata yazdırılır
    }

    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Appointment update(Appointment appointment) {

        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {

        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.appointmentRepo.delete(this.get(id));
        return true;
    }

    @Override
    public List<Appointment> findAll() {
        return this.appointmentRepo.findAll();
    }

    @Override
    public List<Appointment> getAppointmentByDoctorId(Long doctorId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentByAnimalId(Long animalId) {
        return null;
    }

    @Override
    public List<Appointment> getAppointmentsByDateRange(String startDate, String endDate) {
        return null;
    }

    @Override
    public List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndAnimalId(startDate,endDate,animalId);
    }

    @Override
    public List<Appointment> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return this.appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
    }

    @Override
    public List<Appointment> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return this.appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);

    }


}
