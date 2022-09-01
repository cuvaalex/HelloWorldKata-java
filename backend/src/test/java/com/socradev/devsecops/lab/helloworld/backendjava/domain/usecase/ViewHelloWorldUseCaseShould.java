package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldStorage;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ViewHelloWorldUseCaseShould {

    private HelloWorldStorage storage;
    private HelloWorldIdGenerator idGenerator;
    private ViewHelloWorldUseCase useCase;

    @BeforeEach
    public void init(){
        this.storage = new FakeHelloWorldStorage();
        this.idGenerator = new FakeHelloWorldIdGenerator();
        HelloWorldRepository repository = new HelloWorldRepositoryImpl(this.storage, this.idGenerator);
        this.useCase = new ViewHelloWorldUseCase(repository);
    }


    @Test
    public void view_helloworld_given_valid_request() {
        Long helloWorldId = SnowflakeIdGenerator.createDefault(0).next();
        ((FakeHelloWorldIdGenerator)this.idGenerator).add(helloWorldId);
        var name = "Alex";
        var dto = new HelloWorldDto(helloWorldId, name);
        this.storage.add(dto);

        var viewHelloWorldRequest = new ViewHelloWorldRequest(helloWorldId);
        var expectedResponse = new ViewHelloWorldResponse(helloWorldId, name);

        assertThat(this.useCase.handle(viewHelloWorldRequest)).isEqualTo(expectedResponse);

    }

}
