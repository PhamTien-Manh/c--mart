package edu.cmart.util.api;




public class ConstantsApi {

    public static final String VERSION = "/v1";
    public static final String NAME_PROJECT = "/c-mart";

    public static final String BASE_URL = NAME_PROJECT + "/api" + VERSION;
    public static final class Account {
        public static final String ACCOUNT_PATH = BASE_URL + "/accounts";
    }

    public static final class City {
        public static final String CITY_PATH = BASE_URL + "/cities";
    }

    public static final class District {
        public static final String DISTRICT_PATH = BASE_URL + "/districts";
    }

    public static final class Ward {
        public static final String WARD_PATH = BASE_URL + "/wards";
    }

    public static final class TypeVehicle {
        public static final String TYPE_VEHICLE_PATH = BASE_URL + "/type-vehicles";
    }
}
