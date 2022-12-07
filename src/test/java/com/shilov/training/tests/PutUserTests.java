package com.shilov.training.tests;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PutUserTests extends BaseReqresTest {

    @Test(dataProvider = "usersValidId", groups = {"user_operations", "smoke"})
    public void testPutUserValidId(String userId) {
        ValidatableResponse response = userManagementRequests.putUser(userId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200);
        softAssert.assertNotNull(response.extract().body().jsonPath().getString("updatedAt"),
                Messages.BODY.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "usersInvalidId", groups = {"user_operations", "negative"})
    public void testPutUserInvalidId(String userId) {
        Assert.assertNotEquals(userManagementRequests.putUser(userId).extract().statusCode(), 200,
                Messages.CODE.toString());
    }
}
