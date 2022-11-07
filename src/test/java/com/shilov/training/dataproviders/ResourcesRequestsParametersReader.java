package com.shilov.training.dataproviders;

import java.util.Properties;

public class ResourcesRequestsParametersReader extends BaseRequestsParametersPropertyReader {

    private final Properties properties = PropertiesLoader.loadProperties(
            "src/test/resources/resources_requests_parameters.properties");

    private static ResourcesRequestsParametersReader instance;

    public static ResourcesRequestsParametersReader getInstance() {
        if (instance == null) {
            instance = new ResourcesRequestsParametersReader();
        }
        return instance;
    }

    public Object[][] getValidPageNumbers() {
        return getDataForTestNgDataProvider("getResources.valid.pageNumber", properties);
    }

    public Object[][] getInvalidPageNumbers() {
        return getDataForTestNgDataProvider("getResources.invalid.pageNumber", properties);
    }

    public Object[][] getValidResourcesPerPage() {
        return getDataForTestNgDataProvider("getResources.valid.resourcesPerPage", properties);
    }

    public Object[][] getInvalidResourcesPerPage() {
        return getDataForTestNgDataProvider("getResources.invalid.resourcesPerPage", properties);
    }

    public Object[][] getValidResourcesId() {
        return getDataForTestNgDataProvider("getResource.validId", properties);
    }

    public Object[][] getInvalidResourcesId() {
        return getDataForTestNgDataProvider("getResource.invalidId", properties);
    }


}
