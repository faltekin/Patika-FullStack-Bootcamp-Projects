package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.IAppointmentService;
import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentSaveRequest;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/appointments")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;

    public AppointmentController(IAppointmentService appointmentService, IModelMapperService modelMapper, IDoctorService doctorService, IAnimalService animalService) {
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
        this.doctorService = doctorService;
        this.animalService = animalService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get (@PathVariable("id") Long id) {
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)     // TODO Değerlendirme formu 17
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest ){

        return ResultHelper.created(appointmentService.save(appointmentSaveRequest));
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AppointmentResponse>> findAll(){

        return this.appointmentService.findAll();

    }


    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest ){

        return ResultHelper.created(appointmentService.update(appointmentUpdateRequest));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.Ok();
    }


    @GetMapping("/filter/doctor/doctor/{doctorId}/{startDate}/{endDate}")      // TODO Değerlendirme ZAMANLI
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorIdAndDateRange(
            @PathVariable("doctorId") long doctorId,
            @PathVariable("startDate") LocalDateTime startDate,
            @PathVariable("endDate") LocalDateTime endDate) {

        return appointmentService.findByDoctorIdAndAppointmentDateBetween(doctorId,startDate,endDate);
    }

    @GetMapping("/filter/doctor/doctor3/{doctorId}/{startDate}/{endDate}")      // TODO Değerlendirme formu 20 ZAMANSIZ
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorIdAndDateRangeWithoutHour(
            @PathVariable("doctorId") long doctorId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {

        return appointmentService.findByDoctorIdAndAppointmentDateBetweenWithoutHour(doctorId,startDate,endDate);
    }


    @GetMapping("/filter/animal/animal/{animalId}/{startDate}/{endDate}")      // TODO Değerlendirme ZAMANLI
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalIdAndDateRange(
            @PathVariable("animalId") long animalId,
            @PathVariable("startDate") LocalDateTime startDate,
            @PathVariable("endDate") LocalDateTime endDate) {

        return appointmentService.findByAnimalIdAndAppointmentDateBetween(animalId,startDate,endDate);

    }

    @GetMapping("/filter/animal/animal3/{animalId}/{startDate}/{endDate}")      // TODO Değerlendirme formu 19 ZAMANSIZ
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalIdAndDateRangeWithoutHour(
            @PathVariable("animalId") long animalId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {

        return appointmentService.findByAnimalIdAndAppointmentDateBetweenWithoutHour(animalId,startDate,endDate);

    }

}
