package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.business.abstracts.IVaccineService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.AnimalRepo;
import dev.patika.veterinaryManagement.dao.VaccineDao;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineSaveRequest;
import dev.patika.veterinaryManagement.dto.request.vaccine.VaccineUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.VaccineResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.entities.Vaccine;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineDao vaccineDao;
    private final AnimalRepo animalRepo;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;

    public VaccineManager(VaccineDao vaccineDao, AnimalRepo animalRepo, IModelMapperService modelMapper, IAnimalService animalService) {
        this.vaccineDao = vaccineDao;
        this.animalRepo = animalRepo;
        this.modelMapper = modelMapper;
        this.animalService = animalService;
    }


    @Override
    public VaccineResponse save(VaccineSaveRequest request) {

        Vaccine saveVaccine = this.modelMapper.forRequest().map(request,Vaccine.class);
        Animal animal = this.animalService.get(request.getAnimalId());

        Integer animalId = Math.toIntExact(request.getAnimalId());


        // TODO Eğer bu olursa adı üzerinden kontrol yapılır
        //List<Vaccine> isVaccineExist = vaccineDao.findAllByAnimalIdAndNameAndProtectionFinishDateAfter(animalId, request.getCode(), request.getProtectionStartDate());

        // TODO Eğer bu olursa kod üzerinden kontrol yapılır
        //List<Vaccine> isVaccineExist = vaccineDao.findAllByAnimalIdAndCodeAndProtectionFinishDateAfter(animalId, request.getCode(), request.getProtectionStartDate());


        // TODO Eğer hastaya ait aynı tip aşının (adı ve kodu aynı olan aşı) aşı koruyuculuk bitiş tarihi daha gelmemiş ise sisteme yeni aşı girilememelidir.
        // TODO Eğer bu olursa hata vermesi için hem aşı adının hem de aşı kodunun aynı olması gerekiyor
        // TODO Değerlendirme formu 22
        List<Vaccine> isVaccineExist = vaccineDao.findAllByAnimalIdAndCodeAndNameAndProtectionFinishDateAfter(animalId, request.getCode(),request.getName(),request.getProtectionStartDate());


        if (!isVaccineExist.isEmpty()){     // TODO Değerlendirme formu 22
            throw new NotFoundException(Msg.VACCINE_ERROR);
        } else {
            saveVaccine.setAnimal(animal);
            return  this.modelMapper.forResponse().map(this.vaccineDao.save(saveVaccine),VaccineResponse.class);
        }



    }

    @Override
    public VaccineResponse update(VaccineUpdateRequest vaccineUpdateRequest) {

        Animal a = animalRepo.findById(vaccineUpdateRequest.getAnimalId()).orElseThrow(() -> new NotFoundException(Msg.ANIMAL_ID_NOT_FOUND));
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest,Vaccine.class);
        Animal animal = this.animalService.get(vaccineUpdateRequest.getAnimalId());

        Integer animalId = Math.toIntExact(vaccineUpdateRequest.getAnimalId());

        List<Vaccine> isVaccineExist = vaccineDao.findAllByAnimalIdAndCodeAndNameAndProtectionFinishDateAfter(animalId, vaccineUpdateRequest.getCode(),vaccineUpdateRequest.getName(),vaccineUpdateRequest.getProtectionStartDate());
        if (isVaccineExist.isEmpty()){

            updateVaccine.setAnimal(animal);
            return this.modelMapper.forResponse().map(this.vaccineDao.save(updateVaccine),VaccineResponse.class);
        } else {
            throw new NotFoundException(Msg.VACCINE_ERROR);
        }




    }



    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = vaccineDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.VACCINE_NOT_FOUND));
        this.vaccineDao.delete(this.get(id));
        return true;
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.VACCINE_NOT_FOUND));
    }

    @Override
    public ResultData<List<VaccineResponse>> findAll() {

        List<Vaccine> vaccines = this.vaccineDao.findAll();
        List<VaccineResponse> vaccineResponses = vaccines.stream().map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(vaccineResponses);

    }


    @Override
    public ResultData<List<VaccineResponse>> findByAnimalIdAndProtectionStartDateBetween2(Long animalId, LocalDate startDateTime, LocalDate endDateTime) {

        List<Vaccine> vaccineList = vaccineDao.findByAnimalIdAndProtectionStartDateBetween(animalId, startDateTime, endDateTime);
        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
            .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
            .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponseList);

    }



    @Override
    public List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineDao.findByProtectionStartDateBetween(startDate, endDate);
    }





    @Override
    public ResultData<List<VaccineResponse>> findByProtectionFinishDateBetween(LocalDate startDateTime, LocalDate endDateTime) {



        List<Vaccine> vaccineList = vaccineDao.findByProtectionFinishDateBetween(startDateTime,endDateTime);
        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
            .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
            .collect(Collectors.toList());

        if (vaccineResponseList.isEmpty()){
            throw new NotFoundException(Msg.NOT_FOUND_DATE_VACCINE);
        }


        return ResultHelper.success(vaccineResponseList);

    }

    @Override
    public ResultData<List<VaccineResponse>> findByAnimalId(Long animalId) {

        Animal an = animalRepo.findById(animalId).orElseThrow(() -> new NotFoundException(Msg.ANIMAL_NOT_FOUND));

        List<Vaccine> vaccineList = vaccineDao.findByAnimalId(animalId);
        List<VaccineResponse> vaccineResponseList = vaccineList.stream()
         .map(vaccine -> modelMapper.forResponse().map(vaccine, VaccineResponse.class))
         .collect(Collectors.toList());

        return ResultHelper.success(vaccineResponseList);


    }
}
