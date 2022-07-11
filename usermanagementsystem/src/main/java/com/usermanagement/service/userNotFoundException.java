package com.usermanagement.service;

public class userNotFoundException extends Throwable {
    public userNotFoundException(String message) {
        super(message);
    }
}
