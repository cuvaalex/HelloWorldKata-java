package com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;

import java.util.*;
import java.util.stream.Collectors;

public class FakeHelloWorldStorage implements HelloWorldStorage {

    private final Map<Long, String> storage = Collections.synchronizedMap(new HashMap<>());


    @Override
    public Optional<HelloWorldDto> find(Long helloWorldId) {

        if (!storage.containsKey(helloWorldId)) {
            return Optional.empty();
        }
        return Optional.of(new HelloWorldDto(helloWorldId, storage.get(helloWorldId)));
    }

    @Override
    public void add(HelloWorldDto entity) {
        storage.put(entity.helloWorldId(), entity.name());
        System.out.println(storage);
    }

    @Override
    public Optional<List<HelloWorldDto>> findAll() {
        synchronized (storage){
            var entries = Collections.synchronizedSet(this.storage.entrySet());
            var dtos = entries.stream().map(e -> HelloWorldDto.builder()
                    .helloWorldId(e.getKey())
                    .name(e.getValue()).build()).toList();
            return Optional.of(dtos);
        }
    }
}
