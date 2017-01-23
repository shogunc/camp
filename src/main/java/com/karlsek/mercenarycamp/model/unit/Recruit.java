package com.karlsek.mercenarycamp.model.unit;

import com.karlsek.mercenarycamp.model.building.Quarter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Arrays;

@Entity
@DiscriminatorValue("RECRUIT")
public class Recruit extends Unit {

    public static class Builder {

        private String name;
        private Quarter assignedQuarter;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder assignedQuarter(Quarter quarter) {
            this.assignedQuarter = quarter;
            return this;
        }

        public Recruit build() {
            return new Recruit(this);
        }
    }

    private Recruit(Builder builder) {
        name = builder.name;
        level = 1;
        this.assignedQuarter = builder.assignedQuarter;
        this.building = builder.assignedQuarter;
        this.unitType = "RECRUIT";
        tiles = Arrays.asList(Tile.ATTACK, Tile.ATTACK_STAR, Tile.ATTACK_STAR, Tile.SURGE_STAR, Tile.BLOOD, Tile.BLOOD, Tile.BLOOD2);
    }

    public Recruit() {
        // Needed for ORM
    }

}
