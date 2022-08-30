package com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class HelloWorldRepositoryImpl implements HelloWorldRepository{


    private final HelloWorldStorage helloWorldStorage;
    private final HelloWorldIdGenerator helloWorldIdGenerator;

    public HelloWorldRepositoryImpl(HelloWorldStorage helloWorldStorage, HelloWorldIdGenerator helloWorldIdGenerator) {
        this.helloWorldStorage = helloWorldStorage;
        this.helloWorldIdGenerator = helloWorldIdGenerator;
    }

    @Override
    public Optional<HelloWorld> find(Long helloWorldId) {
        var dto = helloWorldStorage.find(helloWorldId);

        if(dto.isEmpty()) {
            return Optional.empty();
        }

        var entity = new HelloWorld(dto.get().helloWorldId(), dto.get().name());
        return Optional.of(entity);
    }

    @Override
    public void add(HelloWorld helloWorld) {
        var dto = new HelloWorldDto(helloWorld.id, helloWorld.name);
        this.helloWorldStorage.add(dto);
    }
}
