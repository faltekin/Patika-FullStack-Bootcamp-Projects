package dev.patika.veterinaryManagement.api;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.result.Result;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.entities.Vaccine;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    private final IAnimalService animalService;
    private final IModelMapperService modelMapperService;
    private final ICustomerService customerService;

    public AnimalController(IAnimalService animalService, IModelMapperService modelMapperService, ICustomerService customerService) {
        this.animalService = animalService;
        this.modelMapperService = modelMapperService;
        this.customerService = customerService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get (@PathVariable("id") Long id) {
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapperService.forResponse().map(animal,AnimalResponse.class));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)     // Değerlendirme formu 12
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest ){
        Animal saveAnimal = this.modelMapperService.forRequest().map(animalSaveRequest,Animal.class);

        Customer customer =this.customerService.get(animalSaveRequest.getCustomerId());
        saveAnimal.setCustomer(customer);

        this.animalService.save(saveAnimal);
        return ResultHelper.created(this.modelMapperService.forResponse().map(saveAnimal,AnimalResponse.class));
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest ){
        Animal updateAnimal = this.modelMapperService.forRequest().map(animalUpdateRequest,Animal.class);

        Customer customer = this.customerService.get(animalUpdateRequest.getCustomerId());
        updateAnimal.setCustomer(customer);

        this.animalService.update(updateAnimal);
        return ResultHelper.success(this.modelMapperService.forResponse().map(updateAnimal,AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.animalService.delete(id);
        return ResultHelper.Ok();
    }


    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)      // Değerlendirme formu 13
    public ResultData<List<AnimalResponse>> getAnimalsByName(@PathVariable("name") String name) {

        List<Animal> animals = this.animalService.getAnimalByName(name);
        List<AnimalResponse> animalResponses = animals.stream().map(animal -> this.modelMapperService.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @GetMapping("/vaccines/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getVaccinesForAnimal(@PathVariable("id") Long id) {

        Animal animal = this.animalService.get(id);
        List<Vaccine> vaccines = animal.getVaccineList();
        List<VaccineResponse> vaccineResponses = vaccines.stream().map(vaccine -> this.modelMapperService.forResponse().map(vaccine, VaccineResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)      // Değerlendirme formu 14
    public ResultData<List<AnimalResponse>> getAnimalsByCustomerId(@PathVariable("id") Long id) {

        List<Animal> animalList = this.animalService.getAnimalByCustomerId(id);
        List<AnimalResponse> animalResponseList = animalList.stream().map(animal -> this.modelMapperService.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(animalResponseList);
    }

}
