package constant;

import util.PropertyReader;

public class TestConstant {
    private static final String PROPERTY_FILE = "testdata.properties";

    public static final String BASE_URL = PropertyReader.getPropertyValue(PROPERTY_FILE, "baseUrl");
    public static final String FIRST_NAME = PropertyReader.getPropertyValue(PROPERTY_FILE, "firstName");
    public static final String LAST_NAME = PropertyReader.getPropertyValue(PROPERTY_FILE, "lastName");
    public static final String STATUS = PropertyReader.getPropertyValue(PROPERTY_FILE, "status");
    public static final String GENDER = PropertyReader.getPropertyValue(PROPERTY_FILE, "gender");
}
