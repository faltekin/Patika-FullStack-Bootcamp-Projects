package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IDoctorService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.DoctorDao;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorSaveRequest;
import dev.patika.veterinaryManagement.dto.request.doctor.DoctorUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.DoctorResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorManager implements IDoctorService {

    private final DoctorDao doctorDao;
    private final IModelMapperService modelMapper;

    public DoctorManager(DoctorDao doctorDao, IModelMapperService modelMapper) {
        this.doctorDao = doctorDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public DoctorResponse save(DoctorSaveRequest doctorSaveRequest) {

        Optional<Doctor> mail = doctorDao.findByMail(doctorSaveRequest.getMail());
        Optional<Doctor> phone = doctorDao.findByPhone(doctorSaveRequest.getPhone());

        if (mail.isPresent()){
            throw new NotFoundException(Msg.DOCTOR_EXIST);
        }
        if (phone.isPresent()){
            throw new NotFoundException(Msg.DOCTOR_EXIST);
        }

        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest,Doctor.class);
        return this.modelMapper.forResponse().map(this.doctorDao.save(saveDoctor),DoctorResponse.class);

    }


    @Override
    public DoctorResponse update(DoctorUpdateRequest doctorUpdateRequest) {


        Optional<Doctor> mail = doctorDao.findByMail(doctorUpdateRequest.getMail());
        Optional<Doctor> phone = doctorDao.findByPhone(doctorUpdateRequest.getPhone());

        if (mail.isPresent()){
            throw new NotFoundException(Msg.DOCTOR_EXIST);
        }
        if (phone.isPresent()){
            throw new NotFoundException(Msg.DOCTOR_EXIST);
        }

        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest,Doctor.class);
        return this.modelMapper.forResponse().map(this.doctorDao.save(updateDoctor),DoctorResponse.class);


    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = doctorDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.DOCTOR_NOT_FOUND));
        this.doctorDao.delete(this.get(id));
        return true;
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorDao.findById(id).orElseThrow(()-> new NotFoundException(Msg.DOCTOR_NOT_FOUND));
    }

    @Override
    public ResultData<List<DoctorResponse>> findAll() {

        List<Doctor> doctors = this.doctorDao.findAll();
        List<DoctorResponse> doctorResponses = doctors.stream().map(doctor -> this.modelMapper.forResponse().map(doctor, DoctorResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(doctorResponses);

    }


}
