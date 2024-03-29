package br.com.sicred.contract.v1.errorhandler;

import br.com.sicred.commons.exception.*;
import br.com.sicred.commons.exception.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalHandlerException {

    @ExceptionHandler({
            EntityAlreadyExistsException.class,
            DuplicateKeyException.class,
            AssociateAlreadyExistsException.class,
            AssociateAlreadyVoteException.class,
            ScheduleAlreadyEndedException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorModel> entityAlreadExists(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorModel.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler({
            EntityNotExistsException.class,
            AssociateNotExistsException.class,
            ScheduleNotExistsException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorModel> EntityNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorModel.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler({AssociateUnableToVoteException.class, ScheduleResultInProgressException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorModel> badRequest(RuntimeException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorModel.builder().message(ex.getMessage()).build());
    }
}
