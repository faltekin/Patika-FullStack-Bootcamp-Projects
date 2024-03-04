package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.entities.Animal;

import java.util.List;

public interface IAnimalService {

    Animal save(Animal animal);
    Animal get(Long id);
    Animal update(Animal animal);
    boolean delete(Long id);
    List<Animal> findAll();
    List<Animal> getAnimalByName(String name);
    List<Animal> getAnimalByCustomerId(Long customerId);

}
