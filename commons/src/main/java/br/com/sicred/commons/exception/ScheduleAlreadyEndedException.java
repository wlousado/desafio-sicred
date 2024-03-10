package br.com.sicred.commons.exception;

public class ScheduleAlreadyEndedException extends RuntimeException {
    public ScheduleAlreadyEndedException(String message) {
        super(message);
    }
}
