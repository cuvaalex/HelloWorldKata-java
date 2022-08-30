package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldStorage;
import org.junit.jupiter.api.BeforeEach;

class ViewHelloWorldUseCaseShould {

    private HelloWorldStorage storage;
    private HelloWorldRepository repository;
    private HelloWorldIdGenerator idGenerator;

    @BeforeEach
    public void init(){
        this.storage = new FakeHelloWorldStorage();
        this.idGenerator = new FakeHelloWorldIdGenerator();
        this.repository = new HelloWorldRepositoryImpl(this.storage, this.idGenerator);
    }


//    @Test
//    public void view_helloworld_given_valid_request() {
//        Long helloWorldId = SnowflakeIdGenerator.createDefault(0).next();
//        ((FakeHelloWorldIdGenerator)this.idGenerator).add(helloWorldId);
//
//
//
//    }

}
