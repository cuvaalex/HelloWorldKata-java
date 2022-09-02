package com.socradev.devsecops.lab.helloworld.backendjava.core.usecase;

import an.awesome.pipelinr.Command;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import org.springframework.stereotype.Component;

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
            throw new ValidationException(ValidationMessage.HELLOWORLDID_DOESNT_EXIST);
        }

        return ViewHelloWorldResponse.builder()
                .helloWorldId(entity.get().id())
                .name(entity.get().name())
                .build();
    }
}
