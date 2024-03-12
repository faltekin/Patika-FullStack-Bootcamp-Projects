package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.IAppointmentService;
import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.AnimalRepo;
import dev.patika.veterinaryManagement.dao.AppointmentRepo;
import dev.patika.veterinaryManagement.dao.AvailableDateRepo;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentManager implements IAppointmentService {

    private final AppointmentRepo appointmentRepo;
    private final AvailableDateRepo availableDateRepo;
    private final DoctorDao doctorDao;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;
    private final AnimalRepo animalRepo;

    public AppointmentManager(AppointmentRepo appointmentRepo, AvailableDateRepo availableDateRepo, DoctorDao doctorDao, IModelMapperService modelMapper, IDoctorService doctorService, IAnimalService animalService, AnimalRepo animalRepo) {
        this.appointmentRepo = appointmentRepo;
        this.availableDateRepo = availableDateRepo;
        this.doctorDao = doctorDao;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
        this.animalService = animalService;
        this.animalRepo = animalRepo;
    }


    @Override
    public AppointmentResponse save(AppointmentSaveRequest request) {

        Animal a = this.animalRepo.findById(request.getAnimalId()).orElseThrow(() -> new NotFoundException(Msg.ANIMAL_NOT_FOUND));
        Doctor d = this.doctorDao.findById(request.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.DOCTOR_NOT_FOUND));


        LocalDateTime appointmentTime = request.getAppointmentDate();
        Long doctorId = request.getDoctorId();

        Appointment saveAppointment = this.modelMapper.forRequest().map(request,Appointment.class);
        Doctor doctor = this.doctorService.get(request.getDoctorId());
        Animal animal = this.animalService.get(request.getAnimalId());


        if(isAvailableDateExist(doctorId, appointmentTime.toLocalDate())) {     // TODO Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_DAY);
        }
        if(isAppointmentExist(doctorId, appointmentTime)) {                     // TODO Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_APPOINTMENT);
        } else {

            saveAppointment.setDoctor(doctor);
            saveAppointment.setAnimal(animal);
            return this.modelMapper.forResponse().map(this.appointmentRepo.save(saveAppointment),AppointmentResponse.class);
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
        return this.appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.APPOINTMENT_DATE_NOT_FOUND));
    }


    @Override
    public AppointmentResponse update(AppointmentUpdateRequest appointmentUpdateRequest) {

        Animal a = this.animalRepo.findById(appointmentUpdateRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(Msg.ANIMAL_NOT_FOUND));
        Doctor d = this.doctorDao.findById(appointmentUpdateRequest.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.DOCTOR_NOT_FOUND));

        LocalDateTime appointmentTime = appointmentUpdateRequest.getAppointmentDate();
        Long doctorId = appointmentUpdateRequest.getDoctorId();

        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest,Appointment.class);
        Animal animal = this.animalService.get(appointmentUpdateRequest.getAnimalId());
        Doctor doctor = this.doctorService.get(appointmentUpdateRequest.getDoctorId());

        if(isAvailableDateExist(doctorId, appointmentTime.toLocalDate())) {     // TODO Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_DAY);
        }
        if(isAppointmentExist(doctorId, appointmentTime)) {                     // TODO Değerlendirme formu 18
            throw new NotFoundException(Msg.DOCTOR_ERROR_APPOINTMENT);
        } else {
            updateAppointment.setAnimal(animal);
            updateAppointment.setDoctor(doctor);

            return this.modelMapper.forResponse().map(this.appointmentRepo.save(updateAppointment),AppointmentResponse.class);
        }


    }

    @Override
    public boolean delete(Long id) {

        Appointment appointment = appointmentRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.APPOINTMENT_DATE_NOT_FOUND));
        this.appointmentRepo.delete(this.get(id));
        return true;
    }

    @Override
    public ResultData<List<AppointmentResponse>> findAll() {
        List<Appointment> appointments = this.appointmentRepo.findAll();
        List<AppointmentResponse> appointmentResponses  = appointments.stream().map(appointment -> this.modelMapper.forResponse().map(appointment, AppointmentResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(appointmentResponses);
    }



    @Override
    public List<Appointment> findByAppointmentDateBetweenAndAnimalId(LocalDateTime startDate, LocalDateTime endDate, Long animalId) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndAnimalId(startDate,endDate,animalId);
    }

    @Override   // TODO
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetweenWithoutHour(Long doctorId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        Optional<Doctor> an = this.doctorDao.findById(doctorId);
        if (an.isEmpty()){
            throw new NotFoundException(Msg.DOCTOR_NOT_FOUND);
        }

        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class)).collect(Collectors.toList());

        if (appointmentResponses.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND_DATE_DOCTOR);
        }


        return ResultHelper.success(appointmentResponses);
    }



    @Override
    public ResultData<List<AppointmentResponse>> findByDoctorIdAndAppointmentDateBetween(Long doctorId, LocalDateTime startDateTime, LocalDateTime endDateTime) {


        Optional<Doctor> an = this.doctorDao.findById(doctorId);
        if (an.isEmpty()){
            throw new NotFoundException(Msg.DOCTOR_NOT_FOUND);
        }

        List<Appointment> appointments = appointmentRepo.findByDoctorIdAndAppointmentDateBetween(doctorId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class)).collect(Collectors.toList());

        if (appointmentResponses.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND_DATE_DOCTOR);
        }

        return ResultHelper.success(appointmentResponses);
    }


    @Override   // TODO
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetweenWithoutHour(Long animalId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        Optional<Animal> an = this.animalRepo.findById(animalId);
        if (an.isEmpty()){
            throw new NotFoundException(Msg.ANIMAL_NOT_FOUND);
        }

        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class)).collect(Collectors.toList());

        if (appointmentResponses.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND_DATE_ANIMAL);
        }


        return ResultHelper.success(appointmentResponses);

    }

    @Override
    public ResultData<List<AppointmentResponse>> findByAnimalIdAndAppointmentDateBetween(Long animalId, LocalDateTime startDateTime, LocalDateTime endDateTime) {

        Optional<Animal> an = this.animalRepo.findById(animalId);
        if (an.isEmpty()){
            throw new NotFoundException(Msg.ANIMAL_NOT_FOUND);
        }

        List<Appointment> appointments = appointmentRepo.findByAnimalIdAndAppointmentDateBetween(animalId, startDateTime, endDateTime);
        List<AppointmentResponse> appointmentResponses = appointments.stream().map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class)).collect(Collectors.toList());

        if (appointmentResponses.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND_DATE_ANIMAL);
        }


        return ResultHelper.success(appointmentResponses);
    }




}
