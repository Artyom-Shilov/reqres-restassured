package com.shilov.training.tests;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PatchResourceTests extends BaseReqresTest {

    @Test(dataProvider = "resourcesValidId")
    public void testPatchResourceValidId(String resourceId) {
        ValidatableResponse response = resourceManagementRequests.patchResource(resourceId);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.extract().statusCode(), 200);
        softAssert.assertNotNull(response.extract().body().jsonPath().getString("updatedAt"));
        softAssert.assertAll();
    }

    @Test(dataProvider = "resourcesInvalidId")
    public void testPatchResourceInvalidId(String resourceId) {
        Assert.assertNotEquals(resourceManagementRequests.patchResource(resourceId).extract().statusCode(), 200);
    }
}
