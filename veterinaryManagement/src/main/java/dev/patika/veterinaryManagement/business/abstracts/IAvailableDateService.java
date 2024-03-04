package dev.patika.veterinaryManagement.business.abstracts;

import dev.patika.veterinaryManagement.entities.AvailableDate;

import java.util.List;

public interface IAvailableDateService {

    AvailableDate save(AvailableDate availableDate,Long id);
    AvailableDate get(Long id);
    boolean delete(Long id);
    List<AvailableDate> findAll();

    AvailableDate update(AvailableDate availableDate);

}
