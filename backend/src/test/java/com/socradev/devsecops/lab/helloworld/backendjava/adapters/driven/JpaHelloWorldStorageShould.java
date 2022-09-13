package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
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
        var hellowordId = getHelloWorldId();
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

    @Test
    public void return_list_of_helloworld() {
        this.storage.add(new HelloWorldDto(getHelloWorldId(), "Alex"));
        this.storage.add(new HelloWorldDto(getHelloWorldId(), "Tim"));
        this.storage.add(new HelloWorldDto(getHelloWorldId(), "John"));

        var retrieveListHelloWorld = this.storage.findAll();

        assertThat(retrieveListHelloWorld).isNotNull().isPresent();
        assertThat(retrieveListHelloWorld.get()).size().isEqualTo(3);

    }

    @Test
    public void return_empty_if_not_any(){
        var retrieveListHelloWorld = this.storage.findAll();
        assertThat(retrieveListHelloWorld).isNotNull().isEmpty();
    }

    private Long getHelloWorldId() {
        return this.idGenerator.next();
    }
}
