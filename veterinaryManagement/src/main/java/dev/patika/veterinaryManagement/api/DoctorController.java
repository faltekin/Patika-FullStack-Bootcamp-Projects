package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public DoctorController(IDoctorService doctorService, IModelMapperService modelMapper) {
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> get (@PathVariable("id") Long id) {
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor,DoctorResponse.class));
    }


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)     // TODO Değerlendirme formu 15
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest ){

        return ResultHelper.created(doctorService.save(doctorSaveRequest));
    }


    @PutMapping("/update")     // TODO YENİ
    @ResponseStatus(HttpStatus.OK)
    public ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest ){

        return ResultHelper.created(doctorService.update(doctorUpdateRequest));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.doctorService.delete(id);
        return ResultHelper.Ok();
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<DoctorResponse>> findAll(){

        return this.doctorService.findAll();

    }


}
