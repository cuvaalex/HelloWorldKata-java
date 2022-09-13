package com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<List<HelloWorld>> findAll() {
        var dtos = helloWorldStorage.findAll();
        if(dtos.isEmpty()){
            return Optional.empty();
        }

        var entities = dtos.get().stream()
                .map(e -> HelloWorld.builder()
                        .id(e.helloWorldId())
                        .name(e.name())
                        .build()).toList();
        return Optional.of(entities);
    }

    @Override
    public void add(HelloWorld entity) {
        var dto = new HelloWorldDto(entity.id(), entity.name());
        this.helloWorldStorage.add(dto);
    }

    @Override
    public Long nextHelloWorldId() {
        return this.helloWorldIdGenerator.next();
    }
}
