package com.rafalazar.bootcamp.app.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.bootcamp.app.client.BankingClient;
import com.rafalazar.bootcamp.app.client.CreditClient;
import com.rafalazar.bootcamp.app.document.Atm;
import com.rafalazar.bootcamp.app.dto.BankingDto;
import com.rafalazar.bootcamp.app.dto.CreditDto;
import com.rafalazar.bootcamp.app.repository.AtmRepository;
import com.rafalazar.bootcamp.app.service.AtmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtmServiceImpl implements AtmService{
	
	@Autowired
	private AtmRepository repo;
	
	@Autowired
	private BankingClient bclient;
	
	@Autowired
	private CreditClient cclient;

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
		
		if(atm.getOperationDate() == null) {
			atm.setOperationDate(new Date());
		}else {
			atm.setOperationDate(atm.getOperationDate());
		}
		
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
	
	@Override
	public Mono<Atm> depositAccountBToAccountC(Double amount, String accountO, String accountD) {
		
		bclient.retiroB(amount, accountO).subscribe();
		cclient.depositC(amount, accountD).subscribe();
		
		Atm a = new Atm();
		
		a.setOperationType("Depósito de Cuenta Bancaria a Cuenta de Crédito");
		a.setNumAccountO(accountO);
		a.setNumAccountD(accountD);
		a.setAmount(amount);
		a.setOperationDate(new Date());
		
		return repo.save(a);
		
		
	}

	@Override
	public Mono<Atm> retiroAccountCToAccountB(Double amount, String accountO, String accountD) {
		
		bclient.depositB(amount, accountD).subscribe();
		cclient.retiroC(amount, accountO).subscribe();
		
		Atm a = new Atm();
		
		a.setNumAccountO(accountO);
		a.setNumAccountD(accountD);
		a.setAmount(amount);
		
		return repo.save(a);
		
	}

	// ----------------- Banking Client ------------------------->
	//--------------
	@Override
	public Flux<BankingDto> findAllBankingProducts() {
		return bclient.findAllBankingProducts();
	}

	@Override
	public Mono<BankingDto> findBankingById(String id) {
		return bclient.findBankingById(id);
	}

	@Override
	public Mono<BankingDto> findByNumAccountB(String numAccount) {
		return bclient.findByNumAccountB(numAccount);
	}
	
	@Override
	public Mono<BankingDto> save(BankingDto banking) {
		return bclient.save(banking);
	}

	@Override
	public Mono<BankingDto> depositB(Double amount, String numAccount) {
		return bclient.depositB(amount, numAccount);
	}

	@Override
	public Mono<BankingDto> retiroB(Double amount, String numAccount) {
		return bclient.retiroB(amount, numAccount);
	}

	//------------------------ Credit Client ---------------------->
	//-----------------------------
	@Override
	public Flux<CreditDto> findAllCreditProducts() {
		return cclient.findAllCreditProducts();
	}

	@Override
	public Mono<CreditDto> findCreditById(String id) {
		return cclient.findCreditById(id);
	}

	@Override
	public Mono<CreditDto> findByNumAccountC(String numberAccount) {
		return cclient.findByNumAccountC(numberAccount);
	}
	
	@Override
	public Mono<CreditDto> save(CreditDto credit) {
		return cclient.save(credit);
	}

	@Override
	public Mono<CreditDto> depositC(Double amount, String numberAccount) {
		return cclient.depositC(amount, numberAccount);
	}

	@Override
	public Mono<CreditDto> retiroC(Double amount, String numberAccount) {
		return cclient.retiroC(amount, numberAccount);
	}
	
}
