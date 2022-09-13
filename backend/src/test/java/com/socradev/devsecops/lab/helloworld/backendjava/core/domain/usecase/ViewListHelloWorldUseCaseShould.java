package com.socradev.devsecops.lab.helloworld.backendjava.core.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewlisthelloworld.ViewListHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.usecase.ViewListHelloWorldUseCase;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ViewListHelloWorldUseCaseShould {
    private HelloWorldStorage storage;
    private HelloWorldIdGenerator idGenerator;
    private ViewListHelloWorldUseCase useCase;

    private HelloWorldRepository repository;


    @BeforeEach
    public void init(){
        this.storage = new FakeHelloWorldStorage();
        this.idGenerator = new FakeHelloWorldIdGenerator();
        this.repository = new HelloWorldRepositoryImpl(this.storage, this.idGenerator);
        this.useCase = new ViewListHelloWorldUseCase(repository);
    }

    @Test
    public void return_a_list_of_helloworld_if_any(){
        var request = new ViewListHelloWorldRequest();
        var idGenerator = SnowflakeIdGenerator.createDefault(5);

        addHelloWorldOnStorage("Alex", idGenerator.next());
        addHelloWorldOnStorage("Tim", idGenerator.next());
        addHelloWorldOnStorage("John", idGenerator.next());


        var response = this.useCase.handle(request);

        assertThat(response).isNotNull();
        assertThat(response.listHelloWorld()).size().isEqualTo(3);
    }

    @Test
    public void return_a_empty_list_if_not_any(){
        var request = new ViewListHelloWorldRequest();
        var response = this.useCase.handle(request);

        assertThat(response).isNotNull();
        assertThat(response.listHelloWorld()).isNotNull().size().isEqualTo(0);

    }

    private void addHelloWorldOnStorage(String name, long helloWorldId) {
        this.storage.add(HelloWorldDto.builder().name(name)
                .helloWorldId(helloWorldId).build());
    }
}
