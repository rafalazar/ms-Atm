package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.Atm;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AtmService {
	
	//Listar todos
	public Flux<Atm> findAll();
	//Buscar por id
	public Mono<Atm> findById(String id);
	//Crear nueva operación
	public Mono<Atm> save(Atm atm);
	//Actualizar operación
	public Mono<Atm> update(Atm atm, String id);
	//Eliminar operación
	public Mono<Void> delete(Atm atm);

}
