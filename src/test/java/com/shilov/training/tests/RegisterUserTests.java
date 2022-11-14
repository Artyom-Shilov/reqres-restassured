package com.shilov.training.tests;

import com.shilov.training.bodies.RegisterUserRequestBody;
import com.shilov.training.models.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegisterUserTests extends BaseReqresTest {

    @Test(dataProvider = "registerUserValidBody")
    public void testBodyWhenRegisterUserValidBody(RegisterUserRequestBody body, User user) {
        userManagementRequests.registerUser(body)
                .body("id", Matchers.equalTo(user.getId().intValue()), "token", Matchers.notNullValue());
    }

    @Test(dataProvider = "registerUserValidBody")
    public void testSchemaWhenRegisterUserValidBody(RegisterUserRequestBody body, User user) {
        userManagementRequests.registerUser(body)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/register_user_response.json"));
    }

    @DataProvider(name = "registerUserValidBody")
    public Object[][] registerUserValidBodyDataProvider() {
        User george = usersReader.getUserById(1);
        User emma = usersReader.getUserById(3);
        User tracey = usersReader.getUserById(6);
        String password = "test";
        return new Object[][] {
                {RegisterUserRequestBody.builder().email(george.getEmail()).password(password).build(), george},
                {RegisterUserRequestBody.builder().email(emma.getEmail()).password(password).build(), emma},
                {RegisterUserRequestBody.builder().email(tracey.getEmail()).password(password).build(), tracey}
        };
    }

    @Test(dataProvider = "registerUserInvalidEmail")
    public void testRegisterUserInvalidEmail(RegisterUserRequestBody body, String caseDescription) {
        ExtractableResponse<Response> response = userManagementRequests.registerUser(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, caseDescription + " status code assertion:");
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), caseDescription + " error message:");
        softAssert.assertAll();
    }

    @DataProvider(name = "registerUserInvalidEmail")
    public Object[][] registerUserInvalidEmailDataProvider() {
        String password = "test";
        return new Object[][]{
                {
                        RegisterUserRequestBody.builder().email("test@gmail.com").password(password).build(),
                        "not reqres email case"
                },
                {
                        RegisterUserRequestBody.builder().email("test").password(password).build(),
                        "not valid email format case"
                },
                {
                        RegisterUserRequestBody.builder().email("").password(password).build(),
                        "empty email case"
                }
                ,
                {
                        RegisterUserRequestBody.builder().password(password).build(),
                        "without email case"
                }
        };
    }

    @Test(dataProvider = "registerUserInvalidPassword")
    public void testRegisterUserInvalidPassword(RegisterUserRequestBody body, String caseDescription) {
        ExtractableResponse<Response> response = userManagementRequests.registerUser(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, caseDescription + " status code assertion:");
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), caseDescription + " error message:");
        softAssert.assertAll();
    }

    @DataProvider(name = "registerUserInvalidPassword")
    public Object[][] registerUserInvalidPasswordDataProvider() {
        String email = "tracey.ramos@reqres.in";
        return new Object[][]{
                {
                        RegisterUserRequestBody.builder().email(email).password("").build(),
                        "empty password case"
                },
                {
                        RegisterUserRequestBody.builder().email(email).build(),
                        "without password case"
                }
        };
    }
}
