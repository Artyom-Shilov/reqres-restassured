package com.shilov.training.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteUserTests extends BaseReqresTest {

    @Test(dataProvider = "resourcesValidId", groups = {"user_operations", "smoke"})
    public void testDeleteUserValidId(String userId) {
        userManagementRequests.deleteUser(userId).statusCode(204);
    }

    @Test(dataProvider = "resourcesInvalidId", groups = {"user_operations", "extended"})
    public void testDeleteUserInvalidId(String userId) {
        Assert.assertNotEquals(userManagementRequests.deleteUser(userId).extract().statusCode(), 204,
                Messages.CODE.toString());
    }
}
