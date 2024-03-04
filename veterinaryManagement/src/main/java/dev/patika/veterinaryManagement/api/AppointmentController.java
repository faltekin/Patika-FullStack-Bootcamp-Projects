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
    @ResponseStatus(HttpStatus.CREATED)     // Değerlendirme formu 17
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest ){
        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);

        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctorId());
        saveAppointment.setDoctor(doctor);

        Animal animal = this.animalService.get(appointmentSaveRequest.getAnimalId());
        saveAppointment.setAnimal(animal);

        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment,AppointmentResponse.class));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest ){
        Appointment updateAppointment = this.modelMapper.forRequest().map(appointmentUpdateRequest,Appointment.class);

        Animal animal = this.animalService.get(appointmentUpdateRequest.getAnimalId());
        updateAppointment.setAnimal(animal);

        Doctor doctor = this.doctorService.get(appointmentUpdateRequest.getDoctorId());
        updateAppointment.setDoctor(doctor);

        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment,AppointmentResponse.class));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.appointmentService.delete(id);
        return ResultHelper.Ok();
    }



    @GetMapping("/filter/doctor/{doctorId}/{startDate}/{endDate}")      // Değerlendirme formu 20
    public ResultData<List<AppointmentResponse>> getAppointmentsByDoctorIdAndDateRange(
            @PathVariable("doctorId") long doctorId,
            @PathVariable("startDate") LocalDateTime startDate,
            @PathVariable("endDate") LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.findByDoctorIdAndAppointmentDateBetween(doctorId, startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }

    @GetMapping("/filter/animal/{animalId}/{startDate}/{endDate}")      // Değerlendirme formu 19
    public ResultData<List<AppointmentResponse>> getAppointmentsByAnimalIdAndDateRange(
            @PathVariable("animalId") long animalId,
            @PathVariable("startDate") LocalDateTime startDate,
            @PathVariable("endDate") LocalDateTime endDate) {
        List<Appointment> appointments = appointmentService.findByAnimalIdAndAppointmentDateBetween(animalId, startDate, endDate);
        List<AppointmentResponse> appointmentResponses = appointments.stream()
                .map(appointment -> modelMapper.forResponse().map(appointment, AppointmentResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(appointmentResponses);
    }




}
