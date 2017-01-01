package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.RecruiterDao;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruiterStatus;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruiterStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Value("${recruitment.time}")
    private Long RECRUITMENT_TIME;
    @Value("${recruitment.cooldown}")
    private Long RECRUITMENT_COOLDOWN;

    @Autowired
    private RecruiterDao recruiterDao;

    @Override
    public Collection<Recruiter> findAll() {
        return recruiterDao.findAll().stream()
                .peek(this::updateStatusAndSave)
                .collect(Collectors.toList());
    }

    @Override
    public Recruiter findOne(Long id) {
        return recruiterDao.findOne(id);
    }

    private void updateStatusAndSave(Recruiter recruiter) {
        recruiter.setStatus(RecruiterStatusUtil.calculateStatus(recruiter));
        recruiterDao.save(recruiter);
    }

    @Override
    public Recruiter sendOnRecruitment(Long id) {
        Recruiter recruiter = recruiterDao.findOne(id);
        if (recruiter == null) {
            //TODO: #2 Create and catch error
            return null;
        } else if (recruiter.getOnRecruitmentUntil() != null && recruiter.getOnRecruitmentUntil().after(Timestamp.from(Instant.now()))) {
            //TODO: #2 Create and catch error
            return null;
        } else if (recruiter.getUnavailableUntil() != null && recruiter.getUnavailableUntil().after(Timestamp.from(Instant.now()))) {
            //TODO: #2 Create and catch error
            return null;
        }
        Long currentDateAsLong = RecruiterStatusUtil.calculateCurrentDateAsLong();
        recruiter.setStatus(RecruiterStatus.RECRUITING);
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
}
