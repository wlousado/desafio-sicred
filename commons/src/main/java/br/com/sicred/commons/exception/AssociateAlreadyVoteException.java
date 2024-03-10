package br.com.sicred.commons.exception;

public class AssociateAlreadyVoteException extends RuntimeException {
    public AssociateAlreadyVoteException(String message) {
        super(message);
    }
}
