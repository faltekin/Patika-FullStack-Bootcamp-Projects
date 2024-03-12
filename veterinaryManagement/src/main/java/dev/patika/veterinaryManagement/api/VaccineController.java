package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.IVaccineService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.appointment.AppointmentUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.entities.Vaccine;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;

    public VaccineController(IVaccineService vaccineService, IModelMapperService modelMapper, IAnimalService animalService) {
        this.vaccineService = vaccineService;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> get (@PathVariable("id") Long id) {
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)     // TODO Değerlendirme formu 21
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest ){

        return ResultHelper.created(vaccineService.save(vaccineSaveRequest));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest ){

        return ResultHelper.created(vaccineService.update(vaccineUpdateRequest));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.vaccineService.delete(id);
        return ResultHelper.Ok();
    }


    @GetMapping("/filter/animal/startdate/{animalId}/{startDate}/{endDate}")    // TODO Başlangıç tarihine göre filtreleme yapılır
    public ResultData<List<VaccineResponse>> getAppointmentsByAnimalIdAndDateRange2(
            @PathVariable("animalId") long animalId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {

        return vaccineService.findByAnimalIdAndProtectionStartDateBetween2(animalId,startDate,endDate);
    }

    @GetMapping("/filter/filter/animal/{startDate}/{endDate}")     // TODO Değerlendirme formu 23 - Bitiş tarihine göre filtreleme yapılır
    public ResultData<List<VaccineResponse>> getAppointmentsByAnimalIdAndDate(
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) {

        return vaccineService.findByProtectionFinishDateBetween(startDate,endDate);
    }

    @GetMapping("/filter/filter/animal/{animalId}")     // TODO Değerlendirme formu 24 - Bir hayvanın tüm aşı kayıtlarını getirir
    public ResultData<List<VaccineResponse>> getAppointmentsByAnimalId(
            @PathVariable("animalId") long animalId)
    {

        return vaccineService.findByAnimalId(animalId);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> findAll(){

        return this.vaccineService.findAll();

    }

}
