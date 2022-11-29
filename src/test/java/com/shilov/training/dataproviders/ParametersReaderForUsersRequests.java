package com.shilov.training.dataproviders;

import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class ParametersReaderForUsersRequests extends BaseRequestsParametersPropertyReader {

    private final Properties properties = PropertiesLoader.loadProperties(
            "src/test/resources/users_requests_parameters.properties");

    public static Object[][] getValidPageNumbersAndUsersNumber() {
        return getDataForTestNgDataProvider("getUsers.valid", properties);
    }


    public Object[][] getInvalidPageNumbersPerPage() {
        return getDataForTestNgDataProvider("getUsers.invalid.pageNumber", properties);
    }

    public Object[][] getInvalidUsersNumbersPerPage() {
        return getDataForTestNgDataProvider("getUsers.invalid.usersNumber", properties);
    }

    public Object[][] getValidUsersId() {
        return getDataForTestNgDataProvider("getUser.validId", properties);
    }

    public Object[][] getInvalidUsersId() {
        return getDataForTestNgDataProvider("getUser.invalidId", properties);
    }
}
