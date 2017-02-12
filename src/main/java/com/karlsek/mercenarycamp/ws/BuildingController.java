package com.karlsek.mercenarycamp.ws;

import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.building.Capacity;
import com.karlsek.mercenarycamp.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @RequestMapping(value = "api/buildings", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Building>> getBuildings() {
        return new ResponseEntity<>(buildingService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/building/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Building> getBuilding(@PathVariable("id") Long id) {
        return new ResponseEntity<>(buildingService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/quarters/capacity", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Capacity> getCapacity() {
        return new ResponseEntity<>(buildingService.findCapacity(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/building", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Building> createBuilding(@RequestBody Building building) {
        return new ResponseEntity<>(buildingService.create(building), HttpStatus.OK);
    }

}
