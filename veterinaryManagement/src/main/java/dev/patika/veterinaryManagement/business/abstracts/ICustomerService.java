package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.entities.Customer;

import java.util.List;

public interface ICustomerService {

    Customer save(Customer customer);
    Customer get(Long id);
    Customer update(Customer customer);

    boolean delete(Long id);
    List<Customer> findAll();
    List<Customer> getCustomerByName(String name);

}
