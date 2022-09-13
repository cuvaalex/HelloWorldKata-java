package com.socradev.devsecops.lab.helloworld.backendjava.system;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class HelloWorldControllerShould {

    @Autowired
    private WebTestClient client;

    @Test
    public void return_helloWorldId_on_given_valid_request(){
        var name = "Alex";
        var request = new HelloWorldRequest(name);

        postHelloWorld(name);
    }

    @Test
    public void return_helloWorld_on_given_valid_request(){
        var name = "Alex";

        HelloWorldResponse response = postHelloWorld(name);

        var helloWorldId = response.helloWorldId();

        var viewResponse = client.get()
                .uri("helloworld/" + helloWorldId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody(ViewHelloWorldResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(viewResponse).isNotNull();
        assertThat(viewResponse.helloWorldId()).isEqualTo(helloWorldId);
        assertThat(viewResponse.name()).isEqualTo(name);
    }

    @Test
    public void return_a_list_of_hello_world() {
        Stream.of("Alex", "john", "Tim")
                .forEach(this::postHelloWorld);

        var viewListResponse = client.get()
                .uri("helloworld/all")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody(ViewListHelloWorldResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(viewListResponse).isNotNull();
        assertThat(viewListResponse.listHelloWorld()).size().isEqualTo(3);
        assertThat(viewListResponse.listHelloWorld().get(0).name()).isEqualTo("Alex");
        assertThat(viewListResponse.listHelloWorld().get(1).name()).isEqualTo("john");
        assertThat(viewListResponse.listHelloWorld().get(2).name()).isEqualTo("Tim");
    }

    private HelloWorldResponse postHelloWorld(String name) {
        var request = new HelloWorldRequest(name);
        var response = client.post()
                .uri("helloworld")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.CREATED)
                .expectBody(HelloWorldResponse.class)
                .returnResult()
                .getResponseBody();

        assertThat(response).isNotNull();
        assertThat(response.helloWorldId()).isGreaterThan(0);
        return response;
    }

}
