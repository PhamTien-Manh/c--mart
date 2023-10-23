package edu.cmart.util;

// Lớp này trả về trạng thái hoạt động:exception, đường dẫn, link dẫn tới client
public class ConstantsException {

    public static final class Exception {
        public static final class Common {
            public static final String INVALID_PARAM_CODE = "0001";
            public static final String INVALID_PARAM = "Invalid request params";
            public static final String ID_MUST_BE_NULL_CODE = "0002";
            public static final String ID_MUST_BE_NULL = "Id must be null when creating new entity";
        }
    }

    public static final class Entity {
        public static final String ENTITY_NOT_FOUND_CODE = "1001";
        public static final String ENTITY_NOT_FOUND = "Entity not found";
        public static final String ENTITY_ALREADY_EXIST_CODE = "1002";
        public static final String ENTITY_ALREADY_EXIST = "Entity already exist";
    }

}
