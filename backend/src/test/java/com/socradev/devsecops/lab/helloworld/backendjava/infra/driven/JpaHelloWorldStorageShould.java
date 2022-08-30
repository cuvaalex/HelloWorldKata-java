package com.socradev.devsecops.lab.helloworld.backendjava.infra.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ContextConfiguration
public class JpaHelloWorldStorageShould {

    private final HelloWorldStorage storage;
    private final HelloWorldIdGenerator idGenerator;

    @Autowired
    public JpaHelloWorldStorageShould(HelloWorldStorage storage, HelloWorldIdGenerator idGenerator) {
        this.storage = storage;
        this.idGenerator = idGenerator;
    }

    @Test
    public void return_empty_given_non_existence_helloworldId(){
        var hellowordId = this.idGenerator.next();
        var helloWorld = this.storage.find(hellowordId);

        assertThat(helloWorld).isEqualTo(Optional.empty());
    }

    @Test
    public void return_added_helloWorld() {
        var id = getHelloWorldId();
        var dto = new HelloWorldDto(id, "Alex");

        this.storage.add(dto);

        var retrieveHelloWorld = this.storage.find(id);

        assertThat(retrieveHelloWorld).isNotNull().isPresent();
        assertThat(retrieveHelloWorld.get()).isEqualTo(dto);

    }

    private Long getHelloWorldId() {
        return this.idGenerator.next();
    }
}
