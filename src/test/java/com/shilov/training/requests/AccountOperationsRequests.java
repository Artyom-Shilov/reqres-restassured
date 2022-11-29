package com.shilov.training.requests;

import com.shilov.training.bodies.AccountOperationRequestBody;
import com.shilov.training.endpoints.ReqresEndpoints;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class AccountOperationsRequests extends BaseRequest {

    private static AccountOperationsRequests instance;

    private AccountOperationsRequests(){}

    public static AccountOperationsRequests getInstance() {
        if (instance == null) {
            instance = new AccountOperationsRequests();
        }
        return instance;
    }

    public ValidatableResponse registerUser(AccountOperationRequestBody body) {
        return given()
                .contentType(ContentType.JSON)
                .spec(baseSpecification)
                .body(body)
                .when()
                .post(ReqresEndpoints.REGISTER_USER.toString())
                .then();
    }

    public ValidatableResponse login(AccountOperationRequestBody body) {
        return given()
                .contentType(ContentType.JSON)
                .spec(baseSpecification)
                .body(body)
                .when()
                .post(ReqresEndpoints.LOGIN.toString())
                .then();
    }

    public ValidatableResponse logout() {
        return given()
                .contentType(ContentType.JSON)
                .spec(baseSpecification)
                .when()
                .post(ReqresEndpoints.LOGOUT.toString())
                .then();
    }
}
