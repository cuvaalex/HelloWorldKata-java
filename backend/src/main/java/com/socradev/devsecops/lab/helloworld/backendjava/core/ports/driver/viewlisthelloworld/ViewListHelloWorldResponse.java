package com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import lombok.Builder;
import lombok.NonNull;
import org.yaml.snakeyaml.util.EnumUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public record ViewListHelloWorldResponse(List<ViewHelloWorldResponse> listHelloWorld) {
    public ViewListHelloWorldResponse(List<ViewHelloWorldResponse> listHelloWorld) {
        this.listHelloWorld = Objects.requireNonNullElseGet(listHelloWorld, ArrayList::new);
    }
}
