package com.karlsek.mercenarycamp.model.building;

import com.karlsek.mercenarycamp.model.unit.Unit;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@DiscriminatorValue("QUARTER")
public class Quarter extends Building {

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    protected Set<Unit> assignedUnits;

}
