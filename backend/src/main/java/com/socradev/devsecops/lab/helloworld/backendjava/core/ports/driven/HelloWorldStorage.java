package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven;

import java.util.Optional;

public interface HelloWorldStorage {
    Optional<HelloWorldDto> find(Long helloWorldId);

    void add(HelloWorldDto helloWorldDto);
}
