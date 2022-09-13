package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven;

import lombok.Builder;

@Builder
public record HelloWorldDto(Long helloWorldId, String name) {
}
