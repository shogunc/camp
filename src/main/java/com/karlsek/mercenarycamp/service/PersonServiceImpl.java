package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.PersonDao;
import com.karlsek.mercenarycamp.model.unit.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Collection<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public List<Person> findUnused(int number) {
            List<Person> persons = findAll().stream()
                    .filter(Person::isUnused)
                    .collect(Collectors.toList());
            Collections.shuffle(persons);
            List<Person> chosenPersons = persons.subList(0, number);
            chosenPersons.forEach(person -> person.setUnused(false));
            return chosenPersons;
    }

}
