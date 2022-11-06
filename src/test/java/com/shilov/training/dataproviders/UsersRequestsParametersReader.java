package com.shilov.training.dataproviders;

import java.util.Properties;

public class UsersRequestsParametersReader extends BaseRequestsParametersPropertyReader {

    private final Properties properties = PropertiesLoader.loadProperties(
            "src/test/resources/users_requests_parameters.properties");

    private static UsersRequestsParametersReader instance;

    public static UsersRequestsParametersReader getInstance() {
        if (instance == null) {
            instance = new UsersRequestsParametersReader();
        }
        return instance;
    }

    public Object[][] getValidPageNumbersAndUsersNumber() {
        return getDataForTestNgDataProvider("getUsers.positive", properties);
    }


    public Object[][] getInvalidPageNumbersPerPage() {
        return getDataForTestNgDataProvider("getUsers.negative.pageNumber", properties);
    }

    public Object[][] getInvalidUsersNumbersPerPage() {
        return getDataForTestNgDataProvider("getUsers.negative.usersNumber", properties);
    }

    public Object[][] getValidUsersId() {
        return getDataForTestNgDataProvider("getUser.validId", properties);
    }

    public Object[][] getInvalidUsersId() {
        return getDataForTestNgDataProvider("getUser.invalidId", properties);
    }
}
