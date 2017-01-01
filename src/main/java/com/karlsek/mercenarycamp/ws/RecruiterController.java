package com.karlsek.mercenarycamp.ws;

import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.unit.Unit;
import com.karlsek.mercenarycamp.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RecruiterController {

    @Autowired
    private RecruiterService recruiterService;

    @RequestMapping(value = "api/recruiters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Recruiter>> getRecruiters() {
        return new ResponseEntity<>(recruiterService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "api/sendOnRecruitment/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Recruiter> sendOnRecruitment(@PathVariable("id") Long id) {
        return new ResponseEntity<>(recruiterService.sendOnRecruitment(id), HttpStatus.OK);
    }

    @RequestMapping(value = "api/inspectRecruits/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Unit>> inspectRecruit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }
}
