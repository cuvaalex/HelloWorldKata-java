package com.socradev.devsecops.lab.helloworld.backendjava.domain;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase.HelloWorldUseCase;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase.ViewHelloWorldUseCase;

public class Facade {

    private final HelloWorldUseCase helloWorldUseCase;
    //private final ViewHelloWorldUseCase viewHelloWorldUseCase;

    public Facade(HelloWorldIdGenerator idGenerator, HelloWorldStorage storage) {
        var helloWorldRepository = new HelloWorldRepositoryImpl(storage, idGenerator);

        this.helloWorldUseCase = new HelloWorldUseCase(helloWorldRepository, idGenerator);
      //  this.viewHelloWorldUseCase = new ViewHelloWorldUseCase(helloWorldRepository, idGenerator);

    }

    public HelloWorldResponse execute(HelloWorldRequest request){ return this.helloWorldUseCase.handle(request); }

    //public ViewHelloWorldResponse execute(ViewHelloWorldRequest request){ return this.viewHelloWorldUseCase.handle(request);}
}
