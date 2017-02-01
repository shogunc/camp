package com.karlsek.mercenarycamp.error;

public class ReservationException extends RuntimeException {

    public ReservationException(REASON reason) {
        super(reason.message);
    }

    public enum REASON {

        NO_SPACE("The chosen quarter does not have any space left"),
        RESERVATION_EXISTS("There is already a reservation for this recruiter"),
        RESERVATION_MISSING("There is no reservation for this recruiter");

        private String message;

        REASON(String message) {
            this.message = message;
        }
    }
}
