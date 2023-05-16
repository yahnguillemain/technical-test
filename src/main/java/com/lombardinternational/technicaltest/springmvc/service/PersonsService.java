package com.lombardinternational.technicaltest.springmvc.service;

import com.lombardinternational.technicaltest.springmvc.domain.Person;
import com.lombardinternational.technicaltest.springmvc.exception.NotFoundException;
import com.lombardinternational.technicaltest.springmvc.exception.PersonNotModifiableException;
import com.lombardinternational.technicaltest.springmvc.mapper.PersonPatcher;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Setter
public class PersonsService {

    private final PersonPatcher patcher;
    private List<Person> persons;

    public PersonsService(PersonPatcher patcher) {
        this.patcher = patcher;
    }

    public List<Person> getAll() {
        return persons;
    }

    public long count() {
        return persons.size();
    }

    public Optional<Person> get(int id) {
        if (id > 0 && persons.size() > id) {
            return Optional.of(persons.get(id));
        } else {
            return Optional.empty();
        }
    }

    public void updatePerson(int id, Person upd) throws NotFoundException, PersonNotModifiableException {
        if (id > 0 && persons.size() > id) {
            if (persons.get(id).getModifiable()) {
                persons.set(id, upd);
            } else {
                throw new PersonNotModifiableException(id);
            }
        } else {
            throw new NotFoundException(id);
        }

    }

    public void patchPerson(int id, Person upd) throws NotFoundException, PersonNotModifiableException {
        if (id > 0 && persons.size() > id) {
            Person target = persons.get(id);
            if (target.getModifiable()) {
                patcher.patch(target, upd);
            } else {
                throw new PersonNotModifiableException(id);
            }
        } else {
            throw new NotFoundException(id);
        }
    }

    public Integer createPerson(Person newPerson) {
        persons.add(newPerson);
        return persons.size() - 1;

    }

    public void deletePerson(int id) {
        persons.remove(id);
    }
}
