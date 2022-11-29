package com.shilov.training.dataproviders;

import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class ParametersReaderForResourcesRequests extends BaseRequestsParametersPropertyReader {

    private final Properties properties = PropertiesLoader.loadProperties(
            "src/test/resources/resources_requests_parameters.properties");

    public static Object[][] getValidPageNumbers() {
        return getDataForTestNgDataProvider("getResources.valid.pageNumber", properties);
    }

    public static Object[][] getInvalidPageNumbers() {
        return getDataForTestNgDataProvider("getResources.invalid.pageNumber", properties);
    }

    public static Object[][] getValidResourcesPerPage() {
        return getDataForTestNgDataProvider("getResources.valid.resourcesPerPage", properties);
    }

    public static Object[][] getInvalidResourcesPerPage() {
        return getDataForTestNgDataProvider("getResources.invalid.resourcesPerPage", properties);
    }

    public static Object[][] getValidResourcesId() {
        return getDataForTestNgDataProvider("getResource.validId", properties);
    }

    public static Object[][] getInvalidResourcesId() {
        return getDataForTestNgDataProvider("getResource.invalidId", properties);
    }


}
