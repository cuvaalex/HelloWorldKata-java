package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldIdGenerator;
import de.mkammerer.snowflakeid.SnowflakeIdGenerator;
import org.springframework.stereotype.Component;

@Component
public class RandomHelloWorldIdGenerator implements HelloWorldIdGenerator {

    private static final int GENERATOR_ID = 0;
    private final SnowflakeIdGenerator generator;

    public RandomHelloWorldIdGenerator() {
        this.generator = SnowflakeIdGenerator.createDefault(GENERATOR_ID);
    }

    @Override
    public Long next() {
        return generator.next();
    }
}
