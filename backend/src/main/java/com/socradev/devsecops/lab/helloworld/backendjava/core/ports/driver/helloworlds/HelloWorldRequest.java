package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import org.apache.commons.lang.StringUtils;

public record HelloWorldRequest(String name) implements Command<HelloWorldResponse> {
}
