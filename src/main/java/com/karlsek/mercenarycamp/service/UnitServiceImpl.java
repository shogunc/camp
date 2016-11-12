package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.BuildingDao;
import com.karlsek.mercenarycamp.dao.UnitDao;
import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDao unitDao;

    @Override
    public Collection<Unit> findAll() {
        return unitDao.findAll();
    }

    @Override
    public Collection<Unit> findUnitsByBuildingId(Long id) {
        return unitDao.findUnitsByBuildingId(id);
    }

    @Override
    public Unit findOne(Long id) {
        return unitDao.findOne(id);
    }

    @Override
    public Unit create(Unit unit) {
        if (unit.getId() != null) {
            return null;
        }
        return unitDao.save(unit);
    }

}
