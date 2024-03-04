package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.IAvailableDateService;
import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/available-dates")
public class AvailableDateController {

    private  final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    public AvailableDateController(IAvailableDateService availableDateService, IDoctorService doctorService, IModelMapperService modelMapper) {
        this.availableDateService = availableDateService;
        this.doctorService = doctorService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get (@PathVariable("id") Long id) {
        AvailableDate availableDate = this.availableDateService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)     // Değerlendirme formu 16
    public ResultData<AvailableDateResponse> save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest ){

        AvailableDate availableDate = this.modelMapper.forRequest().map(availableDateSaveRequest,AvailableDate.class);
        Long doctorId = availableDateSaveRequest.getDoctorId();
        AvailableDate savedDate = this.availableDateService.save(availableDate, doctorId);
        return ResultHelper.created(this.modelMapper.forResponse().map(savedDate, AvailableDateResponse.class));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest ){
        AvailableDate updateDate = this.modelMapper.forRequest().map(availableDateUpdateRequest,AvailableDate.class);

        Doctor doctor = this.doctorService.get(availableDateUpdateRequest.getDoctorId());
        updateDate.setDoctor(doctor);

        this.availableDateService.update(updateDate);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDate,AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.availableDateService.delete(id);
        return ResultHelper.Ok();
    }

}
