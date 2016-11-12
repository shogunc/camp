package com.karlsek.mercenarycamp.ws;

import com.karlsek.mercenarycamp.model.camp.Camp;
import com.karlsek.mercenarycamp.model.unit.Unit;
import com.karlsek.mercenarycamp.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Scope("request")
public class UnitController {

    @Autowired
    private UnitService unitService;

    @Autowired
    private Camp camp;

    @RequestMapping(value = "api/units", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> getUnits() {
        return new ResponseEntity<>(unitService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/units/byBuilding/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> getUnitsByBuildingId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unitService.findUnitsByBuildingId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/units/byEnteredBuilding", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> getUnitsByEnteredBuilding() {
        return new ResponseEntity<>(unitService.findUnitsByBuildingId(camp.getEnteredBuilding()), HttpStatus.OK);
    }

    @RequestMapping(value = "api/unit/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> getUnit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(unitService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/unit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Unit> createUnit(@RequestBody Unit unit) {
        return new ResponseEntity<>(unitService.create(unit), HttpStatus.OK);
    }

}
