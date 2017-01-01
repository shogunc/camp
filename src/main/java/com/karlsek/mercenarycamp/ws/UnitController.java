package com.karlsek.mercenarycamp.ws;

import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruiterStatus;
import com.karlsek.mercenarycamp.model.unit.Unit;
import com.karlsek.mercenarycamp.service.RecruiterService;
import com.karlsek.mercenarycamp.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@Scope("request")
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private RecruiterService recruiterService;

    @RequestMapping(value = "api/units", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> getUnits() {
        return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/units/byBuilding/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> getUnitsByBuildingId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unitService.findUnitsByBuildingId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/unit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> getUnit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unitService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/units/recruit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> recruitUnits(@PathVariable("id") Long recruiterId) {
        Recruiter recruiter = recruiterService.findOne(recruiterId);
        if (recruiter == null || recruiter.getStatus() != RecruiterStatus.RECRUITS_WAITING) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        recruiter.updateStatusAfterInspection();
        return new ResponseEntity<>(unitService.recruitUnits(), HttpStatus.OK);
    }

}
