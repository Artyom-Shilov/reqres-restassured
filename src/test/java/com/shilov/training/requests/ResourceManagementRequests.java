package com.shilov.training.requests;


import com.shilov.training.endpoints.ReqresEndpoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class ResourceManagementRequests extends BaseRequest {

    private static ResourceManagementRequests instance;

    private ResourceManagementRequests() {}

    public static ResourceManagementRequests getInstance() {
        if (instance == null) {
            instance = new ResourceManagementRequests();
        }
        return instance;
    }

    public ValidatableResponse getResources(String pageNumber, String resourcesPerPage) {
        return given()
                .spec(baseSpecification)
                .queryParam("page", pageNumber)
                .queryParam("per_page", resourcesPerPage)
                .when()
                .get(ReqresEndpoints.GET_RESOURCES.toString())
                .then();
    }

    public ValidatableResponse getResources() {
        return given()
                .spec(baseSpecification)
                .when()
                .get(ReqresEndpoints.GET_RESOURCES.toString())
                .then();
    }

    public ValidatableResponse getResourceById(String id) {
        return given()
                .spec(baseSpecification)
                .when()
                .get(ReqresEndpoints.GET_RESOURCE_BY_ID.toString(), id)
                .then();
    }
}
