package edu.cmart.exception.common;

import edu.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static edu.cmart.util.ConstantsException.Exception.Common.FOREIGN_KEY_IS_NOT_FOUND;
import static edu.cmart.util.ConstantsException.Exception.Common.FOREIGN_KEY_IS_NOT_FOUND_CODE;


public class ForeignKeyIsNotFound extends ArchitectureException {

    private static final long serialVersionUID = 1L;

    public ForeignKeyIsNotFound(String field) {
        super();
        this.code = FOREIGN_KEY_IS_NOT_FOUND_CODE;
        this.message = field + FOREIGN_KEY_IS_NOT_FOUND;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
