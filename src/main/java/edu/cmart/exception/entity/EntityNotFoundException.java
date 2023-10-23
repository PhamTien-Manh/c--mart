package edu.cmart.exception.entity;

import edu.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static edu.cmart.util.ConstantsException.Entity.ENTITY_NOT_FOUND;
import static edu.cmart.util.ConstantsException.Entity.ENTITY_NOT_FOUND_CODE;


public class EntityNotFoundException extends ArchitectureException {

    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public EntityNotFoundException() {
        super();
        this.code = ENTITY_NOT_FOUND_CODE;
        this.message = ENTITY_NOT_FOUND;
        this.status = HttpStatus.NOT_FOUND;
    }
}