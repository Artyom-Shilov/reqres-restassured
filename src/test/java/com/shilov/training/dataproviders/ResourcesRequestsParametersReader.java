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

    public Object[][] getValidPageNumbersAndResourcesPerPage() {
        return getDataForTestNgDataProvider("getResources.positive", properties);
    }


}
