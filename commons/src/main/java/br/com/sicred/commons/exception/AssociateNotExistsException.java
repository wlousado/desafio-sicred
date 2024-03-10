package br.com.sicred.commons.exception;

public class AssociateNotExistsException extends RuntimeException {

    private final static String ERROR_ASSOCIATE_NOT_EXISTS = "associate not exists";
    public AssociateNotExistsException(String message) {
        super(message);
    }

    public AssociateNotExistsException(){
        super(ERROR_ASSOCIATE_NOT_EXISTS);
    }
}
