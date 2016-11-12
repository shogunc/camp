package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.BuildingDao;
import com.karlsek.mercenarycamp.model.building.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Override
    public Collection<Building> findAll() {
        return buildingDao.findAll();
    }

    @Override
    public Building findOne(Long id) {
        return buildingDao.findOne(id);
    }

    @Override
    public Building create(Building building) {
        if (building.getId() != null) {
            return null;
        }
        return buildingDao.save(building);
    }

}
