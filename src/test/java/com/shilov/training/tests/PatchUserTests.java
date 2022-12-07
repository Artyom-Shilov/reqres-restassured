package com.shilov.training.tests;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchUserTests extends BaseReqresTest {

    @Test(dataProvider = "usersValidId", groups = {"user_operations", "smoke"})
    public void testPatchUserValidId(String userId) {
        ValidatableResponse response = userManagementRequests.patchUser(userId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, Messages.CODE.toString());
        softAssert.assertNotNull(response.extract().body().jsonPath().getString("updatedAt"),
                Messages.BODY.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "usersInvalidId", groups = {"user_operations", "negative"})
    public void testPatchUserInvalidId(String userId) {
        Assert.assertNotEquals(userManagementRequests.patchUser(userId).extract().statusCode(), 200,
                Messages.CODE.toString());
    }
}
