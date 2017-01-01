package com.karlsek.mercenarycamp.model.building.recruitmentpost;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
public class Recruiter {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RecruiterStatus status;

    private Timestamp onRecruitmentUntil;

    private Timestamp unavailableUntil;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecruiterStatus getStatus() {
        return status;
    }

    public void setStatus(RecruiterStatus status) {
        this.status = status;
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

    public void updateStatusAfterInspection() {
        if (this.getUnavailableUntil().after(Timestamp.from(Instant.now()))) {
            this.setStatus(RecruiterStatus.UNAVAILABLE);
        }
        this.setStatus(RecruiterStatus.AVAILABLE);
    }
}
