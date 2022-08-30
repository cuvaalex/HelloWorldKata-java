package com.socradev.devsecops.lab.helloworld.backendjava.infra.web.controllers.base;

import an.awesome.pipelinr.Pipeline;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseControler {
    @Autowired
    protected Pipeline pipeline;
}
