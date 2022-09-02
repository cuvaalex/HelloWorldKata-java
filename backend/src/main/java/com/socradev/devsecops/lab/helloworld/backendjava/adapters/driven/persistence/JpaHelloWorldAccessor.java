package com.socradev.devsecops.lab.helloworld.backendjava.adapters.driven.persistence;

import org.springframework.data.repository.CrudRepository;

public interface JpaHelloWorldAccessor extends CrudRepository<HelloWorldRecord, Long> {

}
