package com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld;

import lombok.Builder;

@Builder
public record HelloWorld(Long id, String name) {}
