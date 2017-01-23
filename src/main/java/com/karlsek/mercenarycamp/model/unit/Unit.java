package com.karlsek.mercenarycamp.model.unit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.karlsek.mercenarycamp.model.building.Building;
import com.karlsek.mercenarycamp.model.building.Quarter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@DiscriminatorColumn(name="unit_type")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Recruit.class, name = "Recruit")
})
public abstract class Unit {

    private static final int MAX_LEVEL = 10;

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "unit_type", insertable = false, updatable = false)
    String unitType;
    String name;
    int level;
    @ElementCollection(targetClass = Tile.class)
    @CollectionTable(name = "Unit_Tile", joinColumns = @JoinColumn(name = "unit_id"))
    @Column(name = "tile_id")
    @Enumerated(EnumType.STRING)
    List<Tile> tiles;
    @ManyToOne
    @JoinColumn(name = "fk_building_id")
    @JsonIgnore
    protected Building building;
    @ManyToOne
    @JoinColumn(name = "fk_quarter_id")
    @JsonIgnore
    protected Quarter assignedQuarter;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level = Math.min(++level, MAX_LEVEL);
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
