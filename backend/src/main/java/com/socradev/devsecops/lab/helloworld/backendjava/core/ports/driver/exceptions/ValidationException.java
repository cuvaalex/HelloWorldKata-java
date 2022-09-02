package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
