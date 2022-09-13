package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driver.web.controllers;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.driver.web.controllers.base.BaseControler;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController extends BaseControler {

    @CrossOrigin
    @PostMapping("/helloworld")
    public ResponseEntity<HelloWorldResponse> createName(@RequestBody HelloWorldRequest helloWorldRequest){
        var response = pipeline.send(helloWorldRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(value="/helloworld/{helloWorldId}", produces = "application/json")
    public ResponseEntity<ViewHelloWorldResponse> viewHelloWorld(@PathVariable Long helloWorldId){
        var request = new ViewHelloWorldRequest(helloWorldId);

        var response = pipeline.send(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(value="/helloworld/all", produces = "application/json")
    public ResponseEntity<ViewListHelloWorldResponse> viewAllHelloWorld(){
        var response = pipeline.send(ViewListHelloWorldRequest.builder().build());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
