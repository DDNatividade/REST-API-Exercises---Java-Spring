package com.apis.apisjwtswagger.Exceptions;

public class NoPostsFoundException extends RuntimeException {
    public NoPostsFoundException(String message) {
        super(message);
    }
}
