package com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld;

import java.util.Optional;

public interface HelloWorldRepository {

    Optional<HelloWorld> find(Long helloWorldId);

    void add(HelloWorld entity);

    Long nextHelloWorldId();
}
