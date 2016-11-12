package com.karlsek.mercenarycamp.ws;

import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.camp.Camp;
import com.karlsek.mercenarycamp.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * This view controller and the accompanying thymeleaf templates are a temporary solution.
 */
@Controller
@Scope("request")
public class ViewController {

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private Camp camp;

    @RequestMapping(value = "/")
    public String appHomePage(Model model) {
        Collection<Building> buildings = buildingService.findAll();
        model.addAttribute("buildings", buildings);
        return "index";
    }

    @RequestMapping(value = "/enterBuilding/{buildingId}")
    public String enterBuilding(@PathVariable("buildingId") Long buildingId, Model model) {
        camp.setEnteredBuilding(buildingId);
        Building building = buildingService.findOne(buildingId);
//        model.addAttribute("building", building);
        return "redirect:/" + building.getBuildingType().toLowerCase() + ".html";
    }

}
