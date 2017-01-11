package com.karlsek.mercenarycamp.error;

public class RecruiterException extends RuntimeException {

    public RecruiterException(REASON reason) {
        super(reason.message);
    }

    public enum REASON {

        UNKNOWN_ID("There is no recruiter with that id"),
        UNAVAILABLE("Recruiter unavailable"),
        ON_RECRUITMENT("Recruiter is on recruitment");

        private String message;

        REASON(String message) {
            this.message = message;
        }
    }

}
