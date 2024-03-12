package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AppointmentResponse;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.entities.AvailableDate;

import java.util.List;

public interface IAvailableDateService {


    AvailableDate get(Long id);
    boolean delete(Long id);

    ResultData<List<AvailableDateResponse>> findAll();

    AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest);


    AvailableDateResponse save(AvailableDateSaveRequest availableDateSaveRequest);
}
