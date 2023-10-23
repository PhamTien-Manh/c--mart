package edu.cmart.exception.common;

import edu.cmart.exception.core.ArchitectureException;
import org.springframework.http.HttpStatus;

import static edu.cmart.util.ConstantsException.Exception.Common.ID_MUST_BE_NULL_CODE;
import static edu.cmart.util.ConstantsException.Exception.Common.ID_MUST_BE_NULL;
public class IdMustBeNullException extends ArchitectureException {

    //region
    private static final long serialVersionUID = 1L;
    //endregion

    public IdMustBeNullException() {
        super();
        this.code = ID_MUST_BE_NULL_CODE;
        this.message = ID_MUST_BE_NULL;
        this.status = HttpStatus.BAD_REQUEST;
    }

}
