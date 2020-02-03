package com.rafalazar.bootcamp.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.bootcamp.app.document.Atm;
import com.rafalazar.bootcamp.app.service.AtmService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/atm")
public class AtmController {
	
	@Autowired
	private AtmService service;
	
	//LISTAR TODAS LAS OPERACIONES
	@GetMapping("/findAll")
	public Flux<Atm> findAll(){
		return service.findAll();
	}

	// LISTAR UNA OPERACIÓN POR SU ID
	@GetMapping("/findById/{id}")
	public Mono<Atm> findById(@PathVariable("id") String id) {
		return service.findById(id);
	}

	// CREAR UNA OPERACIÓN
	@PostMapping("/create")
	public Mono<ResponseEntity<Atm>> create(@RequestBody Atm atm) {
		return service.save(atm).map(a -> ResponseEntity.created(URI.create("/atm/".concat(a.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(a));
	}
	
	// ACTUALIZAR UNA OPERACIÓN
		@PutMapping("/update/{id}")
		public Mono<ResponseEntity<Atm>> update(@PathVariable("id") String id, @RequestBody Atm atm) {
			return service.update(atm, id)
					.map(a -> ResponseEntity.created(URI.create("/atm/".concat(a.getId())))
							.contentType(MediaType.APPLICATION_JSON).body(a))
					.defaultIfEmpty(ResponseEntity.notFound().build());
		}

		// ELIMINAR UNA OPERACIÓN
		@DeleteMapping("/delete/{id}")
		public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
			return service.findById(id).flatMap(a -> {
				return service.delete(a).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
			}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
		}

}
