package com.socradev.devsecops.lab.helloworld.backendjava.infra.web.controllers;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.web.controllers.base.BaseControler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController extends BaseControler {

    @PostMapping("/helloworld")
    public ResponseEntity<HelloWorldResponse> createName(@RequestBody HelloWorldRequest helloWorldRequest){
        var response = pipeline.send(helloWorldRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @GetMapping("/helloworld/{helloWorldId}")
//    public ResponseEntity<ViewHelloWorldResponse> viewHelloWorld(@RequestBody String helloWorldId){
//        var request = ViewHelloWorldRequest.builder()
//                .helloWorldId(helloWorldId).build();
//
//        var response = pipeline.send(request);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
