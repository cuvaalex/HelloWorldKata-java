package com.socradev.devsecops.lab.helloworld.backendjava.infra.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;

public class FakeHelloWorldIdGenerator implements HelloWorldIdGenerator {
    private final Long helloWorldId;

    public FakeHelloWorldIdGenerator(Long helloWorldId) {
        this.helloWorldId = helloWorldId;
    }

    @Override
    public Long next() {
        return helloWorldId;
    }
}
