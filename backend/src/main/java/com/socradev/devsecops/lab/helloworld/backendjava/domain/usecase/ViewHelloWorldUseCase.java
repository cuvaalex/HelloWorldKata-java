package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import org.springframework.stereotype.Component;

import javax.swing.text.View;

@Component
public class ViewHelloWorldUseCase implements Command.Handler<ViewHelloWorldRequest, ViewHelloWorldResponse> {
    private final HelloWorldRepository helloWorldRepository;

    public ViewHelloWorldUseCase(HelloWorldRepository helloWorldRepository) {
        this.helloWorldRepository = helloWorldRepository;
    }

    @Override
    public ViewHelloWorldResponse handle(ViewHelloWorldRequest viewHelloWorldRequest) {
        var id = viewHelloWorldRequest.helloWorldId();
        var entity = this.helloWorldRepository.find(id);
        if(entity.isEmpty()){
            return ViewHelloWorldResponse.builder().build();
        }

        return ViewHelloWorldResponse.builder()
                .helloWorldId(entity.get().id())
                .name(entity.get().name())
                .build();
    }

}
