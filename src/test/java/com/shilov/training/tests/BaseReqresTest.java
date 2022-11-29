package com.shilov.training.tests;

import com.shilov.training.dataproviders.ResourcesReader;
import com.shilov.training.dataproviders.ParametersReaderForResourcesRequests;
import com.shilov.training.dataproviders.UsersReader;
import com.shilov.training.dataproviders.ParametersReaderForUsersRequests;
import com.shilov.training.requests.AccountOperationsRequests;
import com.shilov.training.requests.ResourceManagementRequests;
import com.shilov.training.requests.UserManagementRequests;

public abstract class BaseReqresTest {

    protected UserManagementRequests userManagementRequests = UserManagementRequests.getInstance();
    protected ResourceManagementRequests resourceManagementRequests = ResourceManagementRequests.getInstance();
    protected AccountOperationsRequests accountOperationsRequests = AccountOperationsRequests.getInstance();


    protected enum Messages {
        CODE("status code assertion"),
        HEADER_CONTENT_TYPE("content type assertion"),
        RESPONSE_BODY("response body assertion"),
        PAGE_NUMBER("page number assertion"),
        USERS_PER_PAGE("users amount per page assertion"),
        USERS_NUMBER("number of users in response assertion"),
        USERS_OBJECTS("users objects assertion"),
        RESOURCES_PER_PAGE("resources amount per page assertion"),
        RESOURCES_NUMBER("number of resources in response assertion"),
        RESOURCES_OBJECTS("resources objects assertion");

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
