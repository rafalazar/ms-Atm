package com.rafalazar.bootcamp.app;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class AppConfig {
	
	@Bean
	@Qualifier("ms-bankingProduct")
	public WebClient getCliente() {
		return WebClient.create("http://localhost:8104/bankingProduct");
	}
	
	@Bean
	@Qualifier("ms-creditProduct")
	public WebClient getCredit() {
		return WebClient.create("http://localhost:8105/creditProduct");
	}

}
