package dev.patika.veterinaryManagement.business.concretes;

import dev.patika.veterinaryManagement.business.abstracts.IVaccineService;
import dev.patika.veterinaryManagement.core.exception.NotFoundException;
import dev.patika.veterinaryManagement.core.utilities.Msg;
import dev.patika.veterinaryManagement.dao.AnimalRepo;
import dev.patika.veterinaryManagement.dao.VaccineDao;
import dev.patika.veterinaryManagement.entities.Animal;
import dev.patika.veterinaryManagement.entities.Doctor;
import dev.patika.veterinaryManagement.entities.Vaccine;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccineManager implements IVaccineService {

    private final VaccineDao vaccineDao;
    private final AnimalRepo animalRepo;

    public VaccineManager(VaccineDao vaccineDao, AnimalRepo animalRepo) {
        this.vaccineDao = vaccineDao;
        this.animalRepo = animalRepo;
    }


    @Override
    public Vaccine save(Vaccine vaccine, Long animalId) {
        Animal animal = animalRepo.findById(animalId).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));


        LocalDate currentDate = LocalDate.now();
        LocalDate protectionFinishDate = vaccine.getProtectionFinishDate();

        if (protectionFinishDate.isBefore(currentDate)){    // Değerlendirme formu 22 - Anlık zaman ile aşı koruyucu bitiş tarihinin karşılaştırması yapılır
            throw new NotFoundException(Msg.VACCINE_ERROR);
        }

        vaccine.setAnimal(animal);
        return vaccineDao.save(vaccine);
    }

    @Override
    public Vaccine update(Vaccine vaccine) {

        this.get(vaccine.getId());
        return this.vaccineDao.save(vaccine);
    }

    @Override
    public boolean delete(Long id) {
        Vaccine vaccine = vaccineDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        this.vaccineDao.delete(this.get(id));
        return true;
    }

    @Override
    public Vaccine get(Long id) {
        return this.vaccineDao.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public List<Vaccine> findAll() {
        return null;
    }

    @Override
    public List<Vaccine> getVaccineByAnimalId(Long animalId) {
        return null;
    }

    @Override
    public List<Vaccine> findByAnimalIdAndProtectionStartDateBetween(Long animalId, LocalDate startDate, LocalDate endDate) {
        return vaccineDao.findByAnimalIdAndProtectionStartDateBetween(animalId, startDate, endDate);
    }

    @Override
    public List<Vaccine> getByProtectionStartDateBetween(LocalDate startDate, LocalDate endDate) {
        return vaccineDao.findByProtectionStartDateBetween(startDate, endDate);
    }
}
