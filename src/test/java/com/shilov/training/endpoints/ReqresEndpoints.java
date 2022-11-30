package com.shilov.training.endpoints;

public enum ReqresEndpoints {

    REGISTER_USER("/register"),
    LOGIN("/login"),
    LOGOUT("/logout"),
    GET_USERS("/users"),
    GET_USER_BY_ID("/users/{id}"),
    GET_RESOURCES("/resource"),
    GET_RESOURCE_BY_ID("/resource/{id}"),
    DELETE_RESOURCE("/resource/{id}"),
    PUT_RESOURCE("/resource/{id}"),
    PATCH_RESOURCE("/resource/{id}"),
    DELETE_USER("/users/{id}"),
    PUT_USER("/users/{id}"),
    PATCH_USER("/users/{id}");

    private String url;

    ReqresEndpoints(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
