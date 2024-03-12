package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.core.result.ResultData;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalSaveRequest;
import dev.patika.veterinaryManagement.dto.request.animal.AnimalUpdateRequest;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerSaveRequest;
import dev.patika.veterinaryManagement.dto.request.customer.CustomerUpdateRequest;
import dev.patika.veterinaryManagement.dto.response.AnimalResponse;
import dev.patika.veterinaryManagement.dto.response.CustomerResponse;
import dev.patika.veterinaryManagement.entities.Customer;

import java.util.List;

public interface ICustomerService {


    CustomerResponse save(CustomerSaveRequest customerSaveRequest);

    Customer get(Long id);

    CustomerResponse update(CustomerUpdateRequest customerUpdateRequest);

    boolean delete(Long id);

    ResultData<List<CustomerResponse>> findAll();

    ResultData<List<CustomerResponse>> getCustomerByName(String name);


}
