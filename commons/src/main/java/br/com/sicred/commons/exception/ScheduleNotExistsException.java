package br.com.sicred.commons.exception;

public class ScheduleNotExistsException extends RuntimeException {
    public ScheduleNotExistsException(String message) {
        super(message);
    }
}
