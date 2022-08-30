package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldStorage;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;


class HelloWorldUseCaseShould {

    private HelloWorldIdGenerator idGenerator;
    private HelloWorldStorage storage;
    private HelloWorldRepository repository;


    @BeforeEach
    public void init() {
        idGenerator = new FakeHelloWorldIdGenerator();
        storage = new FakeHelloWorldStorage();
        repository = new HelloWorldRepositoryImpl(storage, idGenerator);
    }

    @Test
    public void throw_an_exception_for_empty_name() {
        thenExceptionOfType(ValidationException.class)
                .isThrownBy(() -> new HelloWorldRequest(null)).withMessage(ValidationMessage.NAME_IS_EMPTY);
    }

    @Test
    public void not_throw_an_exception_when_not_empty_name() {
        thenNoException().isThrownBy(() -> new HelloWorldRequest("Alex"));
    }

    @Test
    public void create_an_helloworld_given_valid_request() {
        Long helloWorldId = SnowflakeIdGenerator.createDefault(0).next();
        ((FakeHelloWorldIdGenerator) this.idGenerator).add(helloWorldId);
        var name = "Alex";

        var entity = new HelloWorld(helloWorldId, name);
        var request = new HelloWorldRequest(name);
        var expectedResponse = new HelloWorldResponse(helloWorldId);

        var usecase = new HelloWorldUseCase(repository, idGenerator);
        var response = usecase.handle(request);

        assertThat(response).isNotNull().isEqualTo(expectedResponse);
        assertThat(response.helloWorldId()).isEqualTo(helloWorldId);
        assertThat(repository.find(helloWorldId)).isPresent().hasValue(entity);
    }




}
