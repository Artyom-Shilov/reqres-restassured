package com.shilov.training.tests;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchResourceTests extends BaseReqresTest {

    @Test(dataProvider = "resourcesValidId", groups = {"resource_operations", "smoke"})
    public void testPatchResourceValidId(String resourceId) {
        ValidatableResponse response = resourceManagementRequests.patchResource(resourceId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200, Messages.CODE.toString());
        softAssert.assertNotNull(response.extract().body().jsonPath().getString("updatedAt"),
                Messages.BODY.toString());
        softAssert.assertAll();
    }

    @Test(dataProvider = "resourcesInvalidId", groups = {"resource_operations", "negative"})
    public void testPatchResourceInvalidId(String resourceId) {
        Assert.assertNotEquals(resourceManagementRequests.patchResource(resourceId).extract().statusCode(), 200,
                Messages.CODE.toString());
    }
}
