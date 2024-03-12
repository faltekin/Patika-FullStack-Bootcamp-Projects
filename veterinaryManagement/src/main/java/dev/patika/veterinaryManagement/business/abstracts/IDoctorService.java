package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.entities.Doctor;

import java.util.List;

public interface IDoctorService {


    DoctorResponse save(DoctorSaveRequest doctorSaveRequest);

    DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest);



    boolean delete(Long id);
    Doctor get(Long id);
    ResultData<List<DoctorResponse>> findAll();

}
