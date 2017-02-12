package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.BuildingDao;
import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.building.Capacity;
import com.karlsek.mercenarycamp.model.building.Quarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Override
    public Collection<Building> findAll() {
        return buildingDao.findAll();
    }

    @Override
    public Collection<Quarter> findAllQuarters() {
        return findAll().stream()
                .filter(building -> building instanceof Quarter)
                .map(building -> (Quarter)building)
                .collect(Collectors.toList());
    }

    @Override
    public Capacity findCapacity() {
        Capacity capacity = new Capacity();
        Collection<Quarter> quarters = findAllQuarters();
        capacity.setTotalCapacity(findTotalCapacity(quarters));
        capacity.setTotalNumberOfUnits(findTotalNumberOfUnits(quarters));
        capacity.setTotalNumberOfReservedSlots(findTotalNumberOfReservedSlots(quarters));
        return capacity;
    }

    private int findTotalCapacity(Collection<Quarter> quarters) {
        return quarters.size() * Quarter.getCapacity();
    }

    private int findTotalNumberOfUnits(Collection<Quarter> quarters) {
        return quarters.stream()
                .mapToInt(quarter -> quarter.getUnits().size())
                .sum();
    }

    private int findTotalNumberOfReservedSlots(Collection<Quarter> quarters) {
        return quarters.stream()
                .map(quarter -> quarter.getReservedSlots().values())
                .flatMap(Collection::stream)
                .mapToInt(reservedNumber -> reservedNumber)
                .sum();
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
