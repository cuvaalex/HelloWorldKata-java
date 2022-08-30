package com.socradev.devsecops.lab.helloworld.backendjava.infra.driven;

public class FakeException extends RuntimeException {
    public FakeException(String message) {
        super(message);
    }
}
