package com.karlsek.mercenarycamp.model.building.recruitmentpost;

import com.karlsek.mercenarycamp.model.building.Building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RECRUITMENT_POST")
public class RecruitmentPost extends Building {
}
