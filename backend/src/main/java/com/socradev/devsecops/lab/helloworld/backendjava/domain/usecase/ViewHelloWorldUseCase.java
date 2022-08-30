package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;


public class ViewHelloWorldUseCase { //implements Command.Handler<ViewHelloWorldRequest, ViewHelloWorldResponse> {
    private final HelloWorldRepository helloWorldRepository;
    private final HelloWorldIdGenerator idGenerator;

    public ViewHelloWorldUseCase(HelloWorldRepository helloWorldRepository, HelloWorldIdGenerator idGenerator) {
        this.helloWorldRepository = helloWorldRepository;
        this.idGenerator = idGenerator;
    }

    //@Override
    public ViewHelloWorldResponse handle(ViewHelloWorldRequest viewHelloWorldRequest) {
        return null;
    }

    //@Override
    public boolean matches(ViewHelloWorldRequest command) {
      //  return Command.Handler.super.matches(command);
        return false;
    }
}
