package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.RecruiterDao;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private static final Long RECRUITMENT_TIME = 15 * 1000l;
    private static final Long RECRUITMENT_COOLDOWN = 3 * 60 * 1000l;

    @Autowired
    private RecruiterDao recruiterDao;

    @Override
    public Collection<Recruiter> findAll() {
        return recruiterDao.findAll();
    }

    @Override
    public Recruiter sendOnRecruitment(Long id) {
        Recruiter recruiter = recruiterDao.findOne(id);
        if (recruiter == null) {
            //TODO: Create and catch error
        } else if (recruiter.getOnRecruitmentUntil() != null && recruiter.getOnRecruitmentUntil().after(Timestamp.from(Instant.now()))) {
            //TODO: Create and catch error
        } else if (recruiter.getUnavailableUntil() != null && recruiter.getUnavailableUntil().after(Timestamp.from(Instant.now()))) {
            //TODO: Create and catch error
        }
        Long currentDateAsLong = getCurrentDateAsLong();
        recruiter.setOnRecruitmentUntil(new Timestamp(currentDateAsLong + RECRUITMENT_TIME));
        recruiter.setUnavailableUntil(new Timestamp(currentDateAsLong + RECRUITMENT_COOLDOWN));
        return recruiterDao.save(recruiter);
    }

    @Override
    public Recruiter create(Recruiter recruiter) {
        if (recruiter.getId() != null) {
            return null;
        }
        return recruiterDao.save(recruiter);
    }

    private Long getCurrentDateAsLong() {
        return new Date().getTime();
    }

}
