package com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class HelloWorld {

    public final Long id;
    public final String name;

    public HelloWorld(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
