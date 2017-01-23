package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.UnitDao;
import com.karlsek.mercenarycamp.model.building.Quarter;
import com.karlsek.mercenarycamp.model.unit.Recruit;
import com.karlsek.mercenarycamp.model.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDao unitDao;

    @Autowired
    private BuildingService buildingService;

    @Override
    public Collection<Unit> findAll() {
        return unitDao.findAll();
    }

    @Override
    public Collection<Unit> findUnitsByBuildingId(Long id) {
        return unitDao.findUnitsByBuildingId(id);
    }

    @Override
    public Collection<Unit> recruitUnits() {
        List<Unit> units = new ArrayList<>();
        Unit unit1 = new Recruit.Builder()
                .name("Martin Olofsson")
                .assignedQuarter((Quarter)buildingService.findOne(1L))
                .build();
        units.add(unitDao.save(unit1));
        Unit unit2 = new Recruit.Builder()
                .name("Anders Persson")
                .assignedQuarter((Quarter)buildingService.findOne(1L))
                .build();
        units.add(unitDao.save(unit2));
        Unit unit3 = new Recruit.Builder()
                .name("Jonas Hallman")
                .assignedQuarter((Quarter)buildingService.findOne(1L))
                .build();
        units.add(unitDao.save(unit3));
        Unit unit4 = new Recruit.Builder()
                .name("Kai Eriksson")
                .assignedQuarter((Quarter)buildingService.findOne(1L))
                .build();
        units.add(unitDao.save(unit4));
        return units;

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
