package com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeHelloWorldStorage implements HelloWorldStorage {

    private final Map<Long, String> storage = new HashMap<>();


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
    }
}
