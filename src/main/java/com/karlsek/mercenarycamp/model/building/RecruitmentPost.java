package com.karlsek.mercenarycamp.model.building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="RECRUITMENT_POST")
public class RecruitmentPost extends Building {
}
