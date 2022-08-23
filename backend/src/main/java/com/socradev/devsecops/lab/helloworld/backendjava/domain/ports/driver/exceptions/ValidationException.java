package com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
