package com.shilov.training.requests;

import com.shilov.training.endpoints.ReqresEndpoints;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class UserManagementRequests extends BaseRequest {

    private static UserManagementRequests instance;

    private UserManagementRequests(){}

    public static UserManagementRequests getInstance() {
        if (instance == null) {
            instance = new UserManagementRequests();
        }
        return instance;
    }

    public ValidatableResponse getUsers(String pageNumber, String usersPerPage) {
        return given()
                .spec(baseSpecification)
                .queryParam("page", pageNumber)
                .queryParam("per_page", usersPerPage)
                .when()
                .get(ReqresEndpoints.GET_USERS.toString())
                .then();
    }

    public ValidatableResponse getUsers() {
        return given()
                .spec(baseSpecification)
                .when()
                .get(ReqresEndpoints.GET_USERS.toString())
                .then();
    }

    public ValidatableResponse getUserById(String userId) {
        return given()
                .spec(baseSpecification)
                .when()
                .get(ReqresEndpoints.GET_USER_BY_ID.toString(), userId)
                .then();
    }
}
