package com.rafalazar.bootcamp.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.bootcamp.app.document.Atm;

public interface AtmRepository extends ReactiveMongoRepository<Atm, String>{

}
