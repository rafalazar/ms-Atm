package com.rafalazar.bootcamp.app.document;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Document(collection="Atm")
public class Atm {
	
	@Id
	private String id;
	private String bankAtm;
	private String operationType;
	private String numAccountO;
	private String numAccountD;
	private Double amount;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date operationDate;

}
