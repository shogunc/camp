package com.karlsek.mercenarycamp.service;

import com.karlsek.mercenarycamp.dao.RecruiterDao;
import com.karlsek.mercenarycamp.error.RecruiterException;
import com.karlsek.mercenarycamp.error.RecruiterException.REASON;
import com.karlsek.mercenarycamp.model.building.Quarter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.Recruiter;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruiterStatus;
import com.karlsek.mercenarycamp.model.building.recruitmentpost.RecruiterStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.util.Comparator.comparing;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    @Value("${recruitment.time}")
    private Long RECRUITMENT_TIME;
    @Value("${recruitment.cooldown}")
    private Long RECRUITMENT_COOLDOWN;

    @Autowired
    private RecruiterDao recruiterDao;
    @Autowired
    private BuildingService buildingService;

    @Override
    public Collection<Recruiter> findAll() {
        return recruiterDao.findAll().stream()
                .peek(this::updateStatus)
                .collect(Collectors.toList());
    }

    @Override
    public Recruiter findOne(Long id) {
        return recruiterDao.findOne(id);
    }

    private void updateStatus(Recruiter recruiter) {
        recruiter.setStatus(RecruiterStatusUtil.calculateStatus(recruiter));
        recruiterDao.save(recruiter);
    }

    @Override
    public void updateStatusAfterInspection(Recruiter recruiter) {
        recruiter.setStatus(RecruiterStatusUtil.calculateStatusAfterInspection(recruiter));
        recruiterDao.save(recruiter);
    }

    @Override
    public Recruiter sendOnRecruitment(Long id) {

        reserveSlots(id);

        Recruiter recruiter = recruiterDao.findOne(id);
        if (recruiter == null) {
            throw new RecruiterException(REASON.UNKNOWN_ID);
        } else if (recruiter.getOnRecruitmentUntil() != null && recruiter.getOnRecruitmentUntil().after(Timestamp.from(Instant.now()))) {
            throw new RecruiterException(REASON.ON_RECRUITMENT);
        } else if (recruiter.getUnavailableUntil() != null && recruiter.getUnavailableUntil().after(Timestamp.from(Instant.now()))) {
            throw new RecruiterException(REASON.UNAVAILABLE);
        }
        Long currentDateAsLong = RecruiterStatusUtil.calculateCurrentDateAsLong();
        recruiter.setStatus(RecruiterStatus.RECRUITING);
        recruiter.setOnRecruitmentUntil(new Timestamp(currentDateAsLong + RECRUITMENT_TIME));
        recruiter.setUnavailableUntil(new Timestamp(currentDateAsLong + RECRUITMENT_COOLDOWN));
        return recruiterDao.save(recruiter);
    }

    private void reserveSlots(Long id) {
        List<Quarter> quarters = buildingService.findAllQuarters().stream()
                .sorted(comparing(Quarter::getId))
                .collect(Collectors.toList());
        int slotsNeeded = 4;
        for (Quarter quarter : quarters) {
            int slotsBeingReserved = min(quarter.getAvailableSpace(), slotsNeeded);
            quarter.reserveSpace(id, slotsBeingReserved);
            slotsNeeded-=slotsBeingReserved;
            if (slotsNeeded == 0) {
                break;
            }
        }
    }

    @Override
    public Recruiter create(Recruiter recruiter) {
        if (recruiter.getId() != null) {
            return null;
        }
        return recruiterDao.save(recruiter);
    }
}
