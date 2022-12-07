package com.shilov.training.tests;

import com.shilov.training.bodies.AccountOperationRequestBody;
import com.shilov.training.bodies.LoginUserResponseBody;
import com.shilov.training.bodies.RegisterUserResponseBody;
import com.shilov.training.dataproviders.ParametersReaderForAccountRequests;
import com.shilov.training.dataproviders.UsersReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AccountOperationsTests extends BaseReqresTest {

    @Test(dataProvider = "accountRequestsValidBody", groups = {"smoke", "account_operations"})
    public void testRegisterUserResponseBodyWhenValidBody(AccountOperationRequestBody body) {
        RegisterUserResponseBody response = accountOperationsRequests.registerUser(body)
                .extract()
                .as(RegisterUserResponseBody.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(UsersReader.getUserById(Long.parseLong(response.getId())).getEmail(), body.getEmail()
                , "check email by received id");
        softAssert.assertNotNull(response.getToken(), Messages.TOKEN.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "accountRequestsValidBody", groups = {"smoke", "account_operations"})
    public void testRegisterUserRequestSchemaValidBody(AccountOperationRequestBody body) {
        accountOperationsRequests.registerUser(body)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/register_user_response.json"));
    }

    @Test(dataProvider = "accountRequestsValidBody", groups = {"smoke", "account_operations"})
    public void testLoginUserRequestValidBody(AccountOperationRequestBody body) {
        ValidatableResponse response = accountOperationsRequests.login(body);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, Messages.CODE.toString());
        softAssert.assertNotNull(response.extract().as(LoginUserResponseBody.class).getToken(),
                Messages.TOKEN.toString());
    }

    @Test(dataProvider = "accountRequestsValidBody", groups = {"smoke", "account_operations"})
    public void testLogoutRequest(AccountOperationRequestBody body) {
        accountOperationsRequests.login(body);
        accountOperationsRequests.logout().statusCode(200);
    }

    @DataProvider(name = "accountRequestsValidBody")
    public Object[][] accountRequestsValidBodyDataProvider() {
        return ParametersReaderForAccountRequests.getRequestBodiesWithValidData();
    }

    @Test(dataProvider = "accountRequestsInvalidEmails",groups = {"negative", "account_operations"})
    public void testRegisterUserRequestInvalidEmail(AccountOperationRequestBody body) {
        ExtractableResponse<Response> response = accountOperationsRequests.registerUser(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, Messages.CODE.toString());
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), Messages.ERROR_BODY.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "accountRequestsInvalidEmails", groups = {"negative", "account_operations"})
    public void testLoginUserRequestInvalidEmail(AccountOperationRequestBody body) {
        ExtractableResponse<Response> response = accountOperationsRequests.login(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, Messages.CODE.toString());
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), Messages.ERROR_BODY.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "accountRequestsInvalidEmails")
    public Object[][] accountRequestsInvalidEmailDataProvider() {
            return ParametersReaderForAccountRequests.getRequestBodiesWithInvalidEmails();
    }

    @Test(dataProvider = "accountRequestsInvalidPassword", groups = {"negative", "account_operations"})
    public void testRegisterUserInvalidPassword(AccountOperationRequestBody body) {
        ExtractableResponse<Response> response = accountOperationsRequests.registerUser(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, Messages.CODE.toString());
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), Messages.ERROR_BODY.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "accountRequestsInvalidPassword", groups = {"negative", "account_operations"})
    public void testLoginUserInvalidPassword(AccountOperationRequestBody body) {
        ExtractableResponse<Response> response = accountOperationsRequests.login(body).extract();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 400, Messages.CODE.toString());
        softAssert.assertNotNull(response.body().jsonPath().getString("error"), Messages.ERROR_BODY.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "accountRequestsInvalidPassword")
    public Object[][] accountRequestsInvalidPasswordDataProvider() {
        return ParametersReaderForAccountRequests.getRequestBodiesWithInvalidPasswords();
    }
}
