package com.karlsek.mercenarycamp.model.building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QUARTER")
public class Quarter extends Building {

}
