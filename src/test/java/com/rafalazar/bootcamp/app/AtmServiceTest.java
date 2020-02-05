package com.rafalazar.bootcamp.app;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.rafalazar.bootcamp.app.document.Atm;
import com.rafalazar.bootcamp.app.service.AtmService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AtmServiceTest {
	
	@Autowired
	private WebTestClient client;
	
	@Autowired
	private AtmService service;

	@Test
	void findAllAtm() {
		client.get()
		.uri("/atm/findAll")
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Atm.class)
		.consumeWith(res -> {
			List<Atm> atm = res.getResponseBody();
			atm.forEach(a -> {
				System.out.println(a.getOperationDate() + " - " + a.getOperationType());
			});
			
			Assertions.assertThat(atm.size()>0).isTrue();
		});
	}

}
