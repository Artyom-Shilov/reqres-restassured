package com.shilov.training.requests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {

    private static final String REQRES_URI = "https://reqres.in/api";

    protected RequestSpecification baseSpecification =
            new RequestSpecBuilder()
                    .setBaseUri(REQRES_URI)
                    .setRelaxedHTTPSValidation()
                    .build();
}
