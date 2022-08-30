package com.socradev.devsecops.lab.helloworld.backendjava.infra.driven.persistence;

import org.springframework.data.repository.CrudRepository;

public interface JpaHelloWorldAccessor extends CrudRepository<HelloWorldRecord, Long> {

}
