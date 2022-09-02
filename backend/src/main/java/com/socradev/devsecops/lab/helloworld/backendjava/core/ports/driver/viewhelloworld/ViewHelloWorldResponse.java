package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld;

import lombok.Builder;

@Builder
public record ViewHelloWorldResponse(Long helloWorldId, String name) {
}
