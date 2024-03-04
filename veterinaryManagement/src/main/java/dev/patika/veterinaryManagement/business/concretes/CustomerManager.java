package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.ICustomerService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.CustomerDao;
import dev.patika.veterinaryManagement.entities.Appointment;
import dev.patika.veterinaryManagement.entities.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManager implements ICustomerService {

    private final CustomerDao customerDao;

    public CustomerManager(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    @Override
    public Customer save(Customer customer) {
        return this.customerDao.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return this.customerDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Customer update(Customer customer) {

        this.get(customer.getId());
        return this.customerDao.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = customerDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.customerDao.delete(this.get(id));
        return true;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerDao.findAll();
    }

    @Override
    public List<Customer> getCustomerByName(String name) {
        return this.customerDao.findByName(name);
    }
}
