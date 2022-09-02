package com.socradev.devsecops.lab.helloworld.backendjava.core.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.exceptions.ValidationMessage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.usecase.ViewHelloWorldUseCase;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepositoryImpl;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driver.viewhelloworld.ViewHelloWorldResponse;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.fake.driven.FakeHelloWorldStorage;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static com.socradev.devsecops.lab.helloworld.backendjava.core.common.data.MethodSources.NON_POSITIVE_LONGS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.thenExceptionOfType;

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

    @ParameterizedTest
    @MethodSource(NON_POSITIVE_LONGS)
    void should_throw_exception_given_not_valid_account_number(Long helloWorldId){
        var viewHelloWorldRequest = ViewHelloWorldRequest.builder()
                .helloWorldId(helloWorldId).build();
        thenExceptionOfType(ValidationException.class)
                .isThrownBy(() -> this.useCase.handle(viewHelloWorldRequest))
                .withMessage(ValidationMessage.HELLOWORLDID_DOESNT_EXIST);
    }

}
