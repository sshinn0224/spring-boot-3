package com.example.sample.common.types;

public enum ResponseBodyMessageTypes {
    SUCCESS("성공"),
    FAIL("실패");

    private String message;

    ResponseBodyMessageTypes(String message) {
        this.message = message;
    }


}
