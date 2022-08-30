package com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationMessage;
import org.apache.commons.lang.StringUtils;

public record HelloWorldRequest(String name) implements Command<HelloWorldResponse> {

    public HelloWorldRequest {
        if (StringUtils.isBlank(name)) {
            throw new ValidationException(ValidationMessage.NAME_IS_EMPTY);
        }
    }
}
