package com.socradev.devsecops.lab.helloworld.backendjava.core.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.core.usecase.HelloWorldUseCase;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.helloworlds.HelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldStorage;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;


class HelloWorldUseCaseShould {

    private HelloWorldIdGenerator idGenerator;
    private HelloWorldRepository repository;
    private HelloWorldUseCase usecase;


    @BeforeEach
    public void init() {
        idGenerator = new FakeHelloWorldIdGenerator();
        HelloWorldStorage storage = new FakeHelloWorldStorage();
        repository = new HelloWorldRepositoryImpl(storage, idGenerator);
        usecase = new HelloWorldUseCase(repository);
    }

    @Test
    public void throw_an_exception_for_empty_name() {
        var request = new HelloWorldRequest(null);
        thenExceptionOfType(ValidationException.class)
                .isThrownBy(() -> this.usecase.handle(request))
                .withMessage(ValidationMessage.NAME_IS_EMPTY);
    }

    @Test
    public void create_an_helloworld_given_valid_request() {
        Long helloWorldId = SnowflakeIdGenerator.createDefault(0).next();
        ((FakeHelloWorldIdGenerator) this.idGenerator).add(helloWorldId);
        var name = "Alex";

        var entity = new HelloWorld(helloWorldId, name);
        var request = new HelloWorldRequest(name);
        var expectedResponse = new HelloWorldResponse(helloWorldId);


        var response = usecase.handle(request);

        assertThat(response).isNotNull().isEqualTo(expectedResponse);
        assertThat(response.helloWorldId()).isEqualTo(helloWorldId);
        assertThat(repository.find(helloWorldId)).isPresent().hasValue(entity);
    }




}
