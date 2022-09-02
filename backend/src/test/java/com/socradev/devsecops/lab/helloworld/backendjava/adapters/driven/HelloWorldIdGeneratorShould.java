package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven.RandomHelloWorldIdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HelloWorldIdGeneratorShould {

    private RandomHelloWorldIdGenerator randomHelloWorldIdGenerator;

    @BeforeEach
    void setUp() {
        randomHelloWorldIdGenerator = new RandomHelloWorldIdGenerator();
    }

    @Test
    public void return_a_value_on_next() {
        assertThat(randomHelloWorldIdGenerator.next()).isNotNull();
    }

    @Test
    public void return_a_new_value_at_each_next() {
        var helloWorldId1 = randomHelloWorldIdGenerator.next();
        assertThat(helloWorldId1).isNotNull();

        var helloWorldId2 = randomHelloWorldIdGenerator.next();
        assertThat(helloWorldId2).isNotNull();
        assertThat(helloWorldId2).isNotEqualTo(helloWorldId1);
    }
}
