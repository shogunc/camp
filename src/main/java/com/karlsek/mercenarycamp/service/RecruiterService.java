package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;

import java.util.Collection;

public interface RecruiterService {

    Collection<Recruiter> findAll();

    Recruiter findOne(Long id);

    Recruiter sendOnRecruitment(Long id);

    Recruiter create(Recruiter recruiter);

}
