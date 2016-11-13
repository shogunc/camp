package com.karlsek.mercenarycamp.dao;

import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterDao extends JpaRepository<Recruiter, Long> {
}
