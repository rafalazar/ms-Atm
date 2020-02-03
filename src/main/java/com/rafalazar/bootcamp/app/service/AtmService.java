package com.rafalazar.bootcamp.app.service;

import com.rafalazar.bootcamp.app.document.Atm;
import com.rafalazar.bootcamp.app.dto.BankingDto;
import com.rafalazar.bootcamp.app.dto.CreditDto;

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
	
	//------------------ Métodos Propios ------------------------>
	//-----------------------------------------------------------
	public Mono<Atm> depositAccountBToAccountC(Double amount, String accountO, String accountD);
	
	public Mono<Atm> retiroAccountCToAccountB(Double amount, String accountO, String accountD);
	
	//------------------ Métodos BankingClient -------
	public Flux<BankingDto> findAllBankingProducts();
	
	public Mono<BankingDto> findBankingById(String id);
	
	public Mono<BankingDto> findByNumAccountB(String numAccount);
	
	public Mono<BankingDto> depositB(Double amount, String numAccount);
	
	public Mono<BankingDto> retiroB(Double amount, String numAccount);
	
	//------------------ Métodos CreditClient --------
	public Flux<CreditDto> findAllCreditProducts();
	
	public Mono<CreditDto> findCreditById(String id);
	
	public Mono<CreditDto> findByNumAccountC(String numberAccount);
	
	public Mono<CreditDto> depositC(Double amount, String numberAccount);
	
	public Mono<CreditDto> retiroC(Double amount, String numberAccount);

}
