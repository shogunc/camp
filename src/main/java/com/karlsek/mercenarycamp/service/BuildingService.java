package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.building.Capacity;
import com.karlsek.mercenarycamp.model.building.Quarter;

import java.util.Collection;

public interface BuildingService {

    Collection<Building> findAll();

    Collection<Quarter> findAllQuarters();

    Capacity findCapacity();

    Building findOne(Long id);

    Building create(Building building);

}
