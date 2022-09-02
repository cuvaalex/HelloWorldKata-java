package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven;

import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldDto;
import com.socradev.devsecops.lab.helloworld.backendjava.core.ports.driven.HelloWorldStorage;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven.persistence.HelloWorldRecord;
import com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven.persistence.JpaHelloWorldAccessor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaHelloWorldStorage implements HelloWorldStorage {

    private final JpaHelloWorldAccessor accessor;

    public JpaHelloWorldStorage(JpaHelloWorldAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public Optional<HelloWorldDto> find(Long helloWorldId) {
        var record = this.accessor.findById(helloWorldId);

        if(record.isEmpty()) {
            return Optional.empty();
        }

        var dto = new HelloWorldDto(record.get().getId(), record.get().getName());
        return Optional.of(dto);
    }

    @Override
    public void add(HelloWorldDto dto) {
        var record = new HelloWorldRecord();
        record.setId(dto.helloWorldId());
        record.setName(dto.name());
        this.accessor.save(record);
    }
}
