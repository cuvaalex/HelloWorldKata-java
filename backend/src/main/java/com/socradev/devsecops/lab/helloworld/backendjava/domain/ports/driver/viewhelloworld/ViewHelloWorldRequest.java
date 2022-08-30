package com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld;

import an.awesome.pipelinr.Command;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class ViewHelloWorldRequest  {

    private String helloWorldId;
}
