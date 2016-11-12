package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.unit.Unit;

import java.util.Collection;

public interface UnitService {

    Collection<Unit> findAll();

    Collection<Unit> findUnitsByBuildingId(Long id);

    Unit findOne(Long id);

    Unit create(Unit unit);

}
