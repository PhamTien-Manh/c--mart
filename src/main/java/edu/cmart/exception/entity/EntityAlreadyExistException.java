package edu.cmart.exception.entity;

import edu.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static edu.cmart.util.ConstantsException.Entity.ENTITY_ALREADY_EXIST;
import static edu.cmart.util.ConstantsException.Entity.ENTITY_ALREADY_EXIST_CODE;

public class EntityAlreadyExistException extends ArchitectureException {
    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public EntityAlreadyExistException() {
        super();
        this.code = ENTITY_ALREADY_EXIST_CODE;
        this.message = ENTITY_ALREADY_EXIST;
        this.status = HttpStatus.BAD_REQUEST;
    }
}