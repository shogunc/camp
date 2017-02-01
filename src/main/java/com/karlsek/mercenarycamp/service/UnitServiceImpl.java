package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.UnitDao;
import com.karlsek.mercenarycamp.model.building.Quarter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.unit.Recruit;
import com.karlsek.mercenarycamp.model.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UnitServiceImpl implements UnitService {

    @Autowired
    private UnitDao unitDao;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private RecruiterService recruiterService;

    @Override
    public Collection<Unit> findAll() {
        return unitDao.findAll();
    }

    @Override
    public Collection<Unit> findUnitsByBuildingId(Long id) {
        return unitDao.findUnitsByBuildingId(id);
    }

    @Override
    public Collection<Unit> recruitUnits(Recruiter recruiter) {
        List<Quarter> quarters = findAllQuartersWithReservationByRecruiter(recruiter);
        List<Unit> units = fillReservedSlotsWithNewRecruits(recruiter, quarters);
        releaseReservations(recruiter, quarters);
        recruiterService.updateStatusAfterInspection(recruiter);
        return units;

    }

    private List<Unit> fillReservedSlotsWithNewRecruits(Recruiter recruiter, List<Quarter> quarters) {
        List<Unit> units = new ArrayList<>();
        for (Quarter quarter : quarters) {
                IntStream.range(0, quarter.getReservedSlots().get(recruiter.getId()))
                    .forEach(reservation -> units.add(unitDao.save(createRecruit(quarter))));
        }
        return units;
    }

    private List<Quarter> findAllQuartersWithReservationByRecruiter(Recruiter recruiter) {
        return buildingService.findAllQuarters().stream()
                    .filter(quarter -> quarter.getReservedSlots().containsKey(recruiter.getId()))
                    .collect(Collectors.toList());
    }

    private void releaseReservations(Recruiter recruiter, List<Quarter> quarters) {
        for (Quarter quarter : quarters) {
            quarter.releaseReservation(recruiter.getId());
        }
    }

    private Recruit createRecruit(Quarter quarter) {
        return new Recruit.Builder()
                .name("Martin Olofsson")
                .assignedQuarter(quarter)
                .build();
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
