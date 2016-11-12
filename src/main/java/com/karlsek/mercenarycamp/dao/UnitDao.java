package com.karlsek.mercenarycamp.dao;

import com.karlsek.mercenarycamp.model.unit.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitDao extends JpaRepository<Unit, Long> {

    List<Unit> findUnitsByBuildingId(Long id);

}
