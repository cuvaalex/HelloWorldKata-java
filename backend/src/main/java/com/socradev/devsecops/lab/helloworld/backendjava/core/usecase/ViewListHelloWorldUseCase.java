package com.socradev.devsecops.lab.helloworld.backendjava.core.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ViewListHelloWorldUseCase implements Command.Handler<ViewListHelloWorldRequest, ViewListHelloWorldResponse> {

    private final HelloWorldRepository repository;

    public ViewListHelloWorldUseCase(HelloWorldRepository repository) {

        this.repository = repository;
    }

    @Override
    public ViewListHelloWorldResponse handle(ViewListHelloWorldRequest viewListHelloWorldRequest) {
        var entities = this.repository.findAll();

        var listOfView = new ArrayList<ViewHelloWorldResponse>();
        entities.get().forEach(
                e -> listOfView.add(ViewHelloWorldResponse.builder()
                        .helloWorldId(e.id())
                        .name(e.name())
                        .build()));
        return new ViewListHelloWorldResponse(listOfView);
    }
}
