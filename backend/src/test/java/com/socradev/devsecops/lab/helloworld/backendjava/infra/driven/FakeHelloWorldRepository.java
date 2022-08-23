package com.socradev.devsecops.lab.helloworld.backendjava.infra.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeHelloWorldRepository implements HelloWorldRepository {

    private final Map<Long, String> storage = new HashMap<>();


    @Override
    public Optional<HelloWorld> find(Long helloWorldId) {
        var dto = storage.get(helloWorldId);
        if (dto.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new HelloWorld(helloWorldId, dto));
    }

    @Override
    public void add(HelloWorld entity) {
        storage.put(entity.id, entity.name);
    }
}
