package com.socradev.devsecops.lab.helloworld.backendjava.domain.common.guards;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationException;

import java.util.function.BooleanSupplier;

public class StringGuards {

    private final String value;

    public StringGuards(String value) {
        this.value = value;
    }

    public String againstNullOrWhitespace(String message) {
        return against(this::isNullOrWhitespace, message);
    }

    private boolean isNullOrWhitespace() {
        return value == null || value.trim().isEmpty();
    }

    private String against(BooleanSupplier tester, String message) {
        if (tester.getAsBoolean()) {
            throw new ValidationException(message);
        }

        return value;
    }
}
