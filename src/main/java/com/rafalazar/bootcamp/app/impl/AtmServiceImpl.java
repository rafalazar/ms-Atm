package com.rafalazar.bootcamp.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.document.Atm;
import com.rafalazar.bootcamp.app.repository.AtmRepository;
import com.rafalazar.bootcamp.app.service.AtmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtmServiceImpl implements AtmService{
	
	@Autowired
	private AtmRepository repo;

	@Override
	public Flux<Atm> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<Atm> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<Atm> save(Atm atm) {
		return repo.save(atm);
	}

	@Override
	public Mono<Atm> update(Atm atm, String id) {
		return repo.findById(id)
				.flatMap(a -> {
					
					//Cuenta origen
					a.setNumAccountO(atm.getNumAccountO());
					//Cuenta destino
					a.setNumAccountD(atm.getNumAccountD());
					//Monto a enviar
					a.setAmount(atm.getAmount());
					
					//Fecha
					if(atm.getOperationDate() == null) {
						a.setOperationDate(a.getOperationDate());
					}else {
						a.setOperationDate(atm.getOperationDate());
					}
					
					return repo.save(a);
				});
	}

	@Override
	public Mono<Void> delete(Atm atm) {
		return repo.delete(atm);
	}
	
}
