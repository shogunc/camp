package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.model.unit.Person;

import java.util.Collection;
import java.util.List;

public interface PersonService {

    Collection<Person> findAll();

    List<Person> findUnused(int number);

}
