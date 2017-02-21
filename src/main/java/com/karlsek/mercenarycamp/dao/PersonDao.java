package com.karlsek.mercenarycamp.dao;

import com.karlsek.mercenarycamp.model.unit.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {

}
