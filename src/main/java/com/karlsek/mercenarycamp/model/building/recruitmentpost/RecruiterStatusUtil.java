package com.karlsek.mercenarycamp.model.building.recruitmentpost;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

public class RecruiterStatusUtil {
    /**
     * If status was RECRUITING or UNAVAILABLE, status may have changed with time.
     * @param recruiter The recruiter for which to calculate status
     * @return The current correct status of the recruiter
     */
    public static RecruiterStatus calculateStatus(Recruiter recruiter) {
        if (RecruiterStatus.RECRUITING.equals(recruiter.getStatus()) &&
                recruiter.getOnRecruitmentUntil().before(new Timestamp(calculateCurrentDateAsLong()))) {
            return RecruiterStatus.RECRUITS_WAITING;
        } else if (RecruiterStatus.UNAVAILABLE.equals(recruiter.getStatus()) &&
                recruiter.getUnavailableUntil().before(new Timestamp(calculateCurrentDateAsLong()))) {
            return RecruiterStatus.AVAILABLE;
        }
        return recruiter.getStatus();
    }

    /**
     * If status was RECRUITS_WAITING and an inspection was manually triggered, new status
     * can be either AVAILABLE or UNAVAILABLE depending on how long has passed.
     * @param recruiter The recruiter for which to calculate status
     * @return The current correct status of the recruiter
     */
    public static RecruiterStatus calculateStatusAfterInspection(Recruiter recruiter) {
        if (RecruiterStatus.RECRUITS_WAITING.equals(recruiter.getStatus())) {
            return recruiter.getUnavailableUntil().after(Timestamp.from(Instant.now())) ?
                    RecruiterStatus.UNAVAILABLE : RecruiterStatus.AVAILABLE;
        }
        return recruiter.getStatus();
    }

    public static Long calculateCurrentDateAsLong() {
        return new Date().getTime();
    }
}
