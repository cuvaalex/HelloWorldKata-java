package com.socradev.devsecops.lab.helloworld.backendjava.core.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldResponse;
import org.springframework.stereotype.Component;

import static com.socradev.devsecops.lab.helloworld.backendjava.domain.common.Guard.guard;

@Component
public class HelloWorldUseCase implements Command.Handler<HelloWorldRequest, HelloWorldResponse> {

    private final HelloWorldRepository repository;

    public HelloWorldUseCase(HelloWorldRepository repository) {
        this.repository = repository;
    }

    @Override
    public HelloWorldResponse handle(HelloWorldRequest helloWorldRequest) {
        var name = guard(helloWorldRequest.name()).againstNullOrWhitespace(ValidationMessage.NAME_IS_EMPTY);
        var helloWorldId = repository.nextHelloWorldId();
        var entity = HelloWorld.builder()
                .id(helloWorldId)
                .name(name).build();
        this.repository.add(entity);

        return new HelloWorldResponse(helloWorldId);
    }

  }
