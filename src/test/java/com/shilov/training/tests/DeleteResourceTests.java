package com.shilov.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteResourceTests extends BaseReqresTest {

    @Test(dataProvider = "resourcesValidId", groups = {"resource_operations", "smoke"})
    public void testDeleteResourceValidId(String resourceId) {
        resourceManagementRequests.deleteResource(resourceId).statusCode(204);
    }

    @Test(dataProvider = "resourcesInvalidId", groups = {"resource_operations", "extended"})
    public void testDeleteResourceInvalidId(String resourceId) {
        Assert.assertNotEquals(resourceManagementRequests.deleteResource(resourceId).extract().statusCode(), 204);
    }
}
