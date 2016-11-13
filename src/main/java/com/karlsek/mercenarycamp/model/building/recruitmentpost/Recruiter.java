package com.karlsek.mercenarycamp.model.building.recruitmentpost;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Recruiter {

    @Id
    @GeneratedValue
    private Long id;

    private Timestamp onRecruitmentUntil;

    private Timestamp unavailableUntil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getOnRecruitmentUntil() {
        return onRecruitmentUntil;
    }

    public void setOnRecruitmentUntil(Timestamp onRecruitmentUntil) {
        this.onRecruitmentUntil = onRecruitmentUntil;
    }

    public Timestamp getUnavailableUntil() {
        return unavailableUntil;
    }

    public void setUnavailableUntil(Timestamp unavailableUntil) {
        this.unavailableUntil = unavailableUntil;
    }
}
