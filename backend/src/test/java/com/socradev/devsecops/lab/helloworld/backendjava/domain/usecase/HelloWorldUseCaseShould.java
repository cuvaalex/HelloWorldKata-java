package com.socradev.devsecops.lab.helloworld.backendjava.domain.usecase;

import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorld;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.helloworld.HelloWorldRepository;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driven.HelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.exceptions.ValidationException;
import com.socradev.devsecops.lab.helloworld.backendjava.domain.ports.driver.helloworlds.HelloWorldRequest;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldIdGenerator;
import com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.FakeHelloWorldRepository;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.BDDAssertions.*;


class HelloWorldUseCaseShould {

    @Test
    public void should_throw_an_exception_for_empty_name() {
        thenExceptionOfType(ValidationException.class)
                .isThrownBy(() -> new HelloWorldRequest(null));
    }

    @Test
    public void should_not_throw_an_exception_when_not_empty_name() {
        thenNoException().isThrownBy(() -> new HelloWorldRequest("Alex"));
    }

    @Test
    public void should_create_an_hello_world_when_all_is_fine() {
        Long helloWorldId = SnowflakeIdGenerator.createDefault(0).next();
        var request = new HelloWorldRequest("Alex");
        HelloWorldIdGenerator idGenerator = new FakeHelloWorldIdGenerator(helloWorldId);
        HelloWorldRepository repository = new FakeHelloWorldRepository();
        var entity = new HelloWorld(helloWorldId, "Alex");

        var usecase = new HelloWorldUseCase(repository, idGenerator);
        var response = usecase.handle(request);

        assertThat(response).isNotNull();
        assertThat(response.helloWorldId()).isEqualTo(helloWorldId);
        assertThat(repository.find(helloWorldId)).isPresent().hasValue(entity);
    }

}
