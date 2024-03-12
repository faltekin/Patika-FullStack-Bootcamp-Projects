package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagement.core.config.modelMapper.IModelMapperService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.core.utilities.ResultHelper;
import dev.patika.veterinaryManagement.dao.CustomerDao;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerDao customerDao;
    private final IModelMapperService modelMapper;

    public CustomerManager(CustomerDao customerDao, IModelMapperService modelMapper) {
        this.customerDao = customerDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public CustomerResponse save(CustomerSaveRequest customerSaveRequest) {

        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest,Customer.class);

        Optional<Customer> mail = customerDao.findByMail(customerSaveRequest.getMail());
        Optional<Customer> phone = customerDao.findByPhone(customerSaveRequest.getPhone());

        if (mail.isPresent()){
            throw new NotFoundException(Msg.CUSTOMER_EXIST);
        }
        if (phone.isPresent()){
            throw new NotFoundException(Msg.CUSTOMER_EXIST);
        }

        return this.modelMapper.forResponse().map(this.customerDao.save(saveCustomer),CustomerResponse.class);

    }

    @Override
    public Customer get(Long id) {
        return this.customerDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.CUSTOMER_NOT_FOUND));
    }


    @Override
    public CustomerResponse update(CustomerUpdateRequest customerUpdateRequest) {

        Optional<Customer> mail = customerDao.findByMail(customerUpdateRequest.getMail());
        Optional<Customer> phone = customerDao.findByPhone(customerUpdateRequest.getPhone());

        if (mail.isPresent()){
            throw new NotFoundException(Msg.CUSTOMER_EXIST);
        }
        if (phone.isPresent()){
            throw new NotFoundException(Msg.CUSTOMER_EXIST);
        }


        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest,Customer.class);
        return this.modelMapper.forResponse().map(this.customerDao.save(updateCustomer),CustomerResponse.class);

    }

    @Override
    public boolean delete(Long id) {
        Customer customer = customerDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.CUSTOMER_NOT_FOUND));
        this.customerDao.delete(this.get(id));
        return true;
    }

    @Override
    public ResultData<List<CustomerResponse>> findAll() {

        List<Customer> customers = this.customerDao.findAll();
        List<CustomerResponse> customerResponses = customers.stream().map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(customerResponses);

    }


    @Override
    public ResultData<List<CustomerResponse>> getCustomerByName(String name) {

        List<Customer> cs = this.customerDao.findByNameContainingIgnoreCase(name);
        if (cs.isEmpty()){
            throw new NotFoundException(Msg.CUSTOMER_NOT_FOUND);
        }

        List<Customer> customers = this.customerDao.findByNameContainingIgnoreCase(name);
        List<CustomerResponse> customerResponses = customers.stream().map(customer -> this.modelMapper.forResponse().map(customer, CustomerResponse.class)).collect(Collectors.toList());

        return ResultHelper.success(customerResponses);
    }
}
