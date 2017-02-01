package com.karlsek.mercenarycamp.model.building;

import com.karlsek.mercenarycamp.error.ReservationException;
import com.karlsek.mercenarycamp.model.unit.Unit;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Entity
@DiscriminatorValue("QUARTER")
public class Quarter extends Building {

    private static final int capacity = 10;
    @ElementCollection
    private Map<Long, Integer> reservedSlots = new HashMap<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL)
    private Set<Unit> assignedUnits;

    public static int getCapacity() {
        return capacity;
    }

    public Map<Long, Integer> getReservedSlots() {
        return reservedSlots;
    }

    public int getAvailableSpace() {
        return capacity - reservedSlots.values().stream()
                .mapToInt(Integer::intValue)
                .sum() - assignedUnits.size();
    }

    public void reserveSpace(long recruiterId, int requestedSpace) {
        if (requestedSpace > getAvailableSpace()) {
            throw new ReservationException(ReservationException.REASON.NO_SPACE);
        }
        if (reservedSlots.keySet().contains(recruiterId)) {
            throw new ReservationException(ReservationException.REASON.RESERVATION_EXISTS);
        }
        reservedSlots.put(recruiterId, requestedSpace);
    }

    public void releaseReservation(long recruiterId) {
        if (!reservedSlots.keySet().contains(recruiterId)) {
            throw new ReservationException(ReservationException.REASON.RESERVATION_MISSING);
        }
        reservedSlots.remove(recruiterId);
    }

}
