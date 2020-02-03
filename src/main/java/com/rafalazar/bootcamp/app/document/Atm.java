package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Document(collection="Atm")
public class Atm {
	
	@Id
	private String id;
	private String numAccountO;
	private String numAccountD;
	private Double amount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date operationDate;

}
