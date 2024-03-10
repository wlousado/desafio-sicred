package br.com.sicred.commons.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityAlreadyExistsException extends RuntimeException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
