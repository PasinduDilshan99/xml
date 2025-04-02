package com.example.test_xml.exception;

public class NotFoundErrorException extends RuntimeException{
    public NotFoundErrorException(String message) {
        super(message);
    }
}
