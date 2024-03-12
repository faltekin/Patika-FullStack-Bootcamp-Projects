package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.entities.Animal;

import java.util.List;

public interface IAnimalService {


    AnimalResponse save(AnimalSaveRequest animalSaveRequest);
    Animal get(Long id);

    AnimalResponse update(AnimalUpdateRequest animalUpdateRequest);
    boolean delete(Long id);
    ResultData<List<AnimalResponse>> findAll();

    ResultData<List<AnimalResponse>> getAnimalByName(String name);

    ResultData<List<AnimalResponse>> getAnimalByCustomerId(Long customerId);

}
