package az.niarjh.springcource.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import az.niarjh.springcource.models.Person;
import az.niarjh.springcource.repository.PeopleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class PeopleService {
    private PeopleRepository peopleRepository;

    @Transactional
    public List<Person> showAllPeople() {
        return peopleRepository.findAll();
    }

    public Person showPerson(UUID id) {
        return peopleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + id));
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(Person updatedPerson, UUID id) {
        Person personToBeUpdated = peopleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found by id = " + id));
        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
    }

    @Transactional
    public void delete(UUID id) {
        peopleRepository.deleteById(id);
    }
}
