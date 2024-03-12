package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAvailableDateService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.AvailableDateRepo;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateSaveRequest;
import dev.patika.veterinaryManagement.dto.request.availableDate.AvailableDateUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.AvailableDateResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.AvailableDate;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AvailableDateManager implements IAvailableDateService {

    private final AvailableDateRepo availableDateRepo;
    private final DoctorDao doctorDao;
    private final IModelMapperService modelMapper;

    public AvailableDateManager(AvailableDateRepo availableDateRepo, DoctorDao doctorDao, IModelMapperService modelMapper) {
        this.availableDateRepo = availableDateRepo;
        this.doctorDao = doctorDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public AvailableDateResponse save(AvailableDateSaveRequest request) {

        AvailableDate availableDate = this.modelMapper.forRequest().map(request,AvailableDate.class);
        Doctor doctor = doctorDao.findById(request.getDoctorId()).orElseThrow(() -> new NotFoundException(Msg.DOCTOR_NOT_FOUND));
        Optional<AvailableDate> date = availableDateRepo.findByDoctorIdAndAvailableDate(request.getDoctorId(),request.getAvailableDate());
        if (date.isEmpty()){

            availableDate.setDoctor(doctor);
            return this.modelMapper.forResponse().map(availableDateRepo.save(availableDate), AvailableDateResponse.class);
        }
        throw new NotFoundException(Msg.DATE_EXIST);

    }


    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.DATE_NOT_FOUND));
    }

    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.DATE_NOT_FOUND));
        this.availableDateRepo.delete(this.get(id));
        return true;
    }

    @Override
    public ResultData<List<AvailableDateResponse>> findAll() {
        List<AvailableDate> dates = this.availableDateRepo.findAll();
        List<AvailableDateResponse> dateResponses = dates.stream().map(availableDate -> this.modelMapper.forResponse().map(availableDate, AvailableDateResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(dateResponses);
    }

    @Override
    public AvailableDateResponse update(AvailableDateUpdateRequest availableDateUpdateRequest) {

        AvailableDate av = availableDateRepo.findById(availableDateUpdateRequest.getId()).orElseThrow(() -> new NotFoundException(Msg.DATE_NOT_FOUND));


        Long doctorId = availableDateUpdateRequest.getDoctorId();

        AvailableDate updateDate = this.modelMapper.forRequest().map(availableDateUpdateRequest,AvailableDate.class);
        Doctor doctor = doctorDao.findById(doctorId).orElseThrow(() -> new NotFoundException(Msg.DOCTOR_NOT_FOUND));

        this.get(availableDateUpdateRequest.getId());

        Optional<AvailableDate> date = availableDateRepo.findByDoctorIdAndAvailableDate(availableDateUpdateRequest.getDoctorId(),availableDateUpdateRequest.getAvailableDate());
        if (date.isPresent()){
            throw new NotFoundException(Msg.DATE_EXIST);
        }

        updateDate.setDoctor(doctor);
        return this.modelMapper.forResponse().map(this.availableDateRepo.save(updateDate),AvailableDateResponse.class);
    }


}
