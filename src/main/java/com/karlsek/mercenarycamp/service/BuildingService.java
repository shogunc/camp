package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.model.building.Building;

import java.util.Collection;

public interface BuildingService {

    Collection<Building> findAll();

    Building findOne(Long id);

    Building create(Building building);

}
