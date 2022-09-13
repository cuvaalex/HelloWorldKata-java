package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld;

import an.awesome.pipelinr.Command;
import lombok.Builder;

@Builder
public record ViewListHelloWorldRequest() implements Command<ViewListHelloWorldResponse> {

}
