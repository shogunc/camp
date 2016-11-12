package com.karlsek.mercenarycamp.model.building;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("QUARTERS")
public class Quarters extends Building {

}
