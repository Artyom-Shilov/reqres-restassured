package com.shilov.training.tests;

import com.shilov.training.dataproviders.ParametersReaderForUsersRequests;
import com.shilov.training.dataproviders.UsersReader;
import com.shilov.training.models.User;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.shilov.training.tests.BaseReqresTest.Messages.*;

public class GetUserTests extends BaseReqresTest {

    @Test(groups = {"user_operations", "smoke"})
    public void testGetUsersWithoutParameters() {
        ValidatableResponse response = userManagementRequests.getUsers();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.extract().body().jsonPath().getString("page"), "1", PAGE_NUMBER.toString());
        softAssert.assertEquals(response.extract().body().jsonPath().getString("per_page"), "6",
                USERS_PER_PAGE.toString());
        softAssert.assertEquals(
                response.extract().body().jsonPath().getList("data", User.class),
                UsersReader.getUsersInRange(1, 6), USERS_OBJECTS.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "validPageNumbersAndUsersNumbers", groups = {"user_operations", "smoke"})
    public void testGetUsersValidPageNumberAndUsersNumberPerPage(String pageNumber, String usersNumberPerPage) {
        ValidatableResponse response = userManagementRequests.getUsers(pageNumber, usersNumberPerPage);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.extract().body().jsonPath().getString("page"), pageNumber, PAGE_NUMBER.toString());
        softAssert.assertEquals(response.extract().body().jsonPath().getString("per_page"), usersNumberPerPage, USERS_PER_PAGE.toString());
        int userNumberLimit = Integer.parseInt(pageNumber) * Integer.parseInt(usersNumberPerPage);
        softAssert.assertEquals(
                response.extract().body().jsonPath().getList("data", User.class),
                UsersReader.getUsersInRange(userNumberLimit - 1, userNumberLimit), USERS_OBJECTS.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "validPageNumbersAndUsersNumbers")
    public Object[][] validPageNumbersAndUsersNumbersDataProvider() {
        return ParametersReaderForUsersRequests.getValidPageNumbersAndUsersNumber();
    }

    @Test(dataProvider = "invalidUsersNumber", groups = {"user_operations", "negative"})
    public void testGetUsersInvalidUsersNumber(String usersNumber) {
        String defaultValidPageNumber = "2";
        ValidatableResponse response = userManagementRequests.getUsers(defaultValidPageNumber, usersNumber);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.extract().jsonPath().getString("per_page"), usersNumber, USERS_PER_PAGE.toString());
        softAssert.assertEquals(response.extract().jsonPath().getList("data").size(), 0, USERS_NUMBER.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidUsersNumber")
    public Object[][] invalidUsersNumberDataProvider() {
        return ParametersReaderForUsersRequests.getInvalidUsersNumbersPerPage();
    }

    @Test (dataProvider = "invalidPageNumberForUsers", groups = {"user_operations", "negative"})
    public void testGetUsersInvalidPageNumber(String pageNumber) {
        String defaultValidUsersNumberPerPage = "2";
        ValidatableResponse response = userManagementRequests.getUsers(pageNumber, defaultValidUsersNumberPerPage);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, CODE.toString());
        softAssert.assertEquals(response.extract().jsonPath().getString("page"), pageNumber, PAGE_NUMBER.toString());
        softAssert.assertEquals(response.extract().jsonPath().getList("data").size(), 0, USERS_NUMBER.toString());
        softAssert.assertAll();
    }

    @DataProvider(name = "invalidPageNumberForUsers")
    public Object[][] invalidPageNumberForUsersDataProvider() {
        return ParametersReaderForUsersRequests.getInvalidPageNumbersPerPage();
    }

    @Test(dataProvider = "usersValidId", groups = {"user_operations", "smoke"})
    public void testGetUserByIdValidIdValue(String userId) {
        ValidatableResponse response = userManagementRequests.getUserById(userId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200);
        softAssert.assertEquals(
                response.extract().jsonPath().getObject("data", User.class),
                UsersReader.getUserById(Long.parseLong(userId)), USERS_OBJECTS.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "usersInvalidId", groups = {"user_operations", "negative"})
    public void testGetUserByIdInvalidIdValue(String userId) {
        ValidatableResponse response = userManagementRequests.getUserById(userId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 404, CODE.toString());
        softAssert.assertEquals(response.extract().body().asString(), "{}", RESPONSE_BODY.toString());
        softAssert.assertAll();
    }
}
