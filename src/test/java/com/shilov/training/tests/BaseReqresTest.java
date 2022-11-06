package com.shilov.training.tests;

import com.shilov.training.requests.UserManagementRequests;
import org.testng.asserts.SoftAssert;

public abstract class BaseReqresTest {

    protected UserManagementRequests userManagementRequests = UserManagementRequests.getInstance();

    protected enum Messages {
        CODE("status code assertion"),
        RESPONSE_BODY("response body assertion"),
        PAGE_NUMBER("page number assertion"),
        USERS_PER_PAGE("users number per page assertion"),
        USERS_NUMBER("number of users in response assertion"),
        USERS_OBJECTS("users objects assertion");




        private String messageText;

        Messages(String messageText) {
            this.messageText = messageText;
        }

        @Override
        public String toString() {
            return messageText;
        }
        }
}
