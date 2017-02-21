package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.UnitDao;
import com.karlsek.mercenarycamp.model.building.Quarter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.unit.Person;
import com.karlsek.mercenarycamp.model.unit.Recruit;
import com.karlsek.mercenarycamp.model.unit.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    @Autowired
    private PersonService personService;

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
        List<Unit> units = new ArrayList<>();
        List<Quarter> quarters = findAllQuartersWithReservationByRecruiter(recruiter);

        for (Quarter quarter : quarters) {
            units.addAll(recruitForQuarter(quarter, quarter.getReservedSlots().get(recruiter.getId())));
        }

        releaseReservations(recruiter, quarters);
        recruiterService.updateStatusAfterInspection(recruiter);
        return units;
    }

    private List<Unit> recruitForQuarter(Quarter quarter, int numberOfSlots) {
        List<Person> persons = personService.findUnused(numberOfSlots);
        return IntStream.range(0, numberOfSlots)
                .boxed()
                .map(slot -> unitDao.save(createRecruit(quarter, persons.remove(0))))
                .collect(Collectors.toList());
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

    private Recruit createRecruit(Quarter quarter, Person person) {
        return new Recruit.Builder()
                .person(person)
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
