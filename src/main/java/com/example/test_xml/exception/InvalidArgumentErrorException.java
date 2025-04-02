package com.example.test_xml.exception;

public class InvalidArgumentErrorException extends RuntimeException{
    public InvalidArgumentErrorException(String message) {
        super(message);
    }
}
