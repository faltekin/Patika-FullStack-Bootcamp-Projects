package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.AnimalRepo;
import dev.patika.veterinaryManagement.dao.CustomerDao;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.entities.Vaccine;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final CustomerDao customerDao;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    public AnimalManager(AnimalRepo animalRepo, CustomerDao customerDao, IModelMapperService modelMapper, ICustomerService customerService) {
        this.animalRepo = animalRepo;
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
        this.customerService = customerService;
    }

    @Override
    public AnimalResponse save(AnimalSaveRequest request) {


        Customer cs = this.customerDao.findById(request.getCustomerId()).orElseThrow(() -> new NotFoundException(Msg.CUSTOMER_NOT_FOUND));

        Animal saveAnimal = this.modelMapper.forRequest().map(request,Animal.class);
        Customer customer =this.customerService.get(request.getCustomerId());


       //Optional<Animal> animalOptional = animalRepo.findByName(request.getName());
        // TODO müşeri id ve hayvan ismi ile kontrol yapılır - farklı müşterinin aynı isimli hayvanı olabilir
        Optional<Animal> animalOptional = animalRepo.findByCustomerIdAndName(request.getCustomerId(),request.getName());
       if (animalOptional.isPresent()){
           throw new NotFoundException(Msg.ANIMAL_EXIST);
       }

        saveAnimal.setCustomer(customer);
        return this.modelMapper.forResponse().map(this.animalRepo.save(saveAnimal),AnimalResponse.class);
    }


    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.ANIMAL_NOT_FOUND));
    }


    @Override
    public AnimalResponse update(AnimalUpdateRequest request) {

        Animal updateAnimal = this.modelMapper.forRequest().map(request,Animal.class);
        Customer customer = this.customerService.get(request.getCustomerId());

        Animal an = this.animalRepo.findById(request.getId()).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));


        Optional<Animal> animalOptional = animalRepo.findByCustomerIdAndName(request.getCustomerId(),request.getName());
        if (animalOptional.isPresent()){
            throw new NotFoundException(Msg.ANIMAL_EXIST);
        }

        updateAnimal.setCustomer(customer);
        return this.modelMapper.forResponse().map(this.animalRepo.save(updateAnimal),AnimalResponse.class);

    }


    @Override
    public boolean delete(Long id) {

        Animal animal = animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.ANIMAL_ID_NOT_FOUND));
        this.animalRepo.delete(animal);  // animalRepo delete kullan
        return true;
    }

    @Override
    public ResultData<List<AnimalResponse>> findAll() {

        List<Animal> animals = this.animalRepo.findAll();
        List<AnimalResponse> animalResponses = animals.stream().map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(animalResponses);


    }

    @Override
    public ResultData<List<AnimalResponse>> getAnimalByName(String name) {

        List<Animal> an = this.animalRepo.findByNameContainingIgnoreCase(name);
        if (an.isEmpty()){
            throw new NotFoundException(Msg.ANIMAL_NOT_FOUND);
        }

        List<Animal> animals = this.animalRepo.findByNameContainingIgnoreCase(name);
        List<AnimalResponse> animalResponses = animals.stream().map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }

    @Override
    public ResultData<List<AnimalResponse>> getAnimalByCustomerId(Long customerId) {


        Customer cs = this.customerDao.findById(customerId).orElseThrow(() -> new NotFoundException(Msg.CUSTOMER_NOT_FOUND));

        List<Animal> animalList = this.animalRepo.findByCustomerId(customerId);
        List<AnimalResponse> animalResponseList = animalList.stream().map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(animalResponseList);
    }
}
