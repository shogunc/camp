package com.karlsek.mercenarycamp.dao;

import com.karlsek.mercenarycamp.model.building.Building;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingDao extends JpaRepository<Building, Long> {

}
