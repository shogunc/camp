package com.karlsek.mercenarycamp.model.building;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruitmentPost;
import com.karlsek.mercenarycamp.model.unit.Unit;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "building")
@DiscriminatorColumn(name="building_type")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Quarters.class, name = "Quarters"),
        @JsonSubTypes.Type(value = RecruitmentPost.class, name = "RecruitmentPost"),
        @JsonSubTypes.Type(value = NoticeBoard.class, name = "NoticeBoard")
})
public abstract class Building {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "building_type", insertable = false, updatable = false)
    private String buildingType;
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    protected Set<Unit> units;

    public Set<Unit> getUnits() {
        return units;
    }

    public void setUnits(Set<Unit> units) {
        this.units = units;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }
}
