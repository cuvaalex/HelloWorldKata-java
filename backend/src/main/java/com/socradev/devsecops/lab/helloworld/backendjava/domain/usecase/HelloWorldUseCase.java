package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldResponse;

import static com.socradev.devsecops.lab.helloworld.backendjava.domain.common.Guard.guard;

public class HelloWorldUseCase implements Command.Handler<HelloWorldRequest, HelloWorldResponse> {

    private final HelloWorldRepository repository;
    private final HelloWorldIdGenerator idGenerator;

    public HelloWorldUseCase(HelloWorldRepository repository, HelloWorldIdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

    @Override
    public HelloWorldResponse handle(HelloWorldRequest helloWorldRequest) {
        var name = guard(helloWorldRequest.name()).againstNullOrWhitespace(ValidationMessage.NAME_IS_EMPTY);

        var helloWorldId = idGenerator.next();

        var entity = new HelloWorld(helloWorldId, name);

        this.repository.add(entity);

        return new HelloWorldResponse(helloWorldId);
    }

    @Override
    public boolean matches(HelloWorldRequest command) {
        return Command.Handler.super.matches(command);
    }
}
