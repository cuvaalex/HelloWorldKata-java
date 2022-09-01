package com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld;


import an.awesome.pipelinr.Command;
import lombok.Builder;

@Builder
public record ViewHelloWorldRequest(Long helloWorldId) implements Command<ViewHelloWorldResponse> {

}
