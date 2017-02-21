package com.karlsek.mercenarycamp.model.unit;

import javax.persistence.*;

@Entity
@Table
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private boolean unused;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public boolean isUnused() {
        return unused;
    }

    public void setUnused(boolean unused) {
        this.unused = unused;
    }
}
