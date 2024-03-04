package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IAnimalService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.AnimalRepo;
import dev.patika.veterinaryManagement.dao.CustomerDao;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Customer;
import dev.patika.veterinaryManagement.entities.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalManager implements IAnimalService {

    private final AnimalRepo animalRepo;
    private final CustomerDao customerDao;

    public AnimalManager(AnimalRepo animalRepo, CustomerDao customerDao) {
        this.animalRepo = animalRepo;
        this.customerDao = customerDao;
    }


    @Override
    public Animal save(Animal animal) {
        return animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Animal update(Animal animal) {

        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {

        Animal animal = animalRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.animalRepo.delete(animal);  // animalRepo delete kullan
        return true;
    }

    @Override
    public List<Animal> findAll() {

        return this.animalRepo.findAll();
    }

    @Override
    public List<Animal> getAnimalByName(String name) {
        return this.animalRepo.findByName(name);
    }

    @Override
    public List<Animal> getAnimalByCustomerId(Long customerId) {
        return this.animalRepo.findByCustomerId(customerId);
    }
}
