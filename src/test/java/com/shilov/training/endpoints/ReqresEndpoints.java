package com.shilov.training.endpoints;

public enum ReqresEndpoints {

    REGISTER_USER("/register"),
    GET_USERS("/users"),
    GET_USER_BY_ID("/users/{id}");

    private String url;

    ReqresEndpoints(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
