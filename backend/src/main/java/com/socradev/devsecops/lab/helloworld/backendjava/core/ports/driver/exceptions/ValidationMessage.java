package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationMessage {

    public static final String NAME_IS_EMPTY = "Name is null or empty";
    public static final String HELLOWORLDID_DOESNT_EXIST = "HelloWorldId doesn't exist or is invalid";
}
