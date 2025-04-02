package com.example.test_xml.exception;

public class ValidationErrorException extends RuntimeException{
    public ValidationErrorException(String message) {
        super(message);
    }
}
