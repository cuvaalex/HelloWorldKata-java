package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driver.web.controllers;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.driver.web.controllers.base.BaseControler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController extends BaseControler {

    @PostMapping("/helloworld")
    public ResponseEntity<HelloWorldResponse> createName(@RequestBody HelloWorldRequest helloWorldRequest){
        var response = pipeline.send(helloWorldRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/helloworld/{helloWorldId}")
    public ResponseEntity<ViewHelloWorldResponse> viewHelloWorld(@PathVariable Long helloWorldId){
        var request = new ViewHelloWorldRequest(helloWorldId);

        var response = pipeline.send(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
