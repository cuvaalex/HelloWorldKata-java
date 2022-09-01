package com.socradev.devsecops.lab.helloworld.backendjava.system;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

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
    }

    @Test
    public void return_helloWorld_on_given_valid_request(){
        var name = "Alex";
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

}
