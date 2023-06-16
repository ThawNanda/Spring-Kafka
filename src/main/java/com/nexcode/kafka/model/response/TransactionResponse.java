package com.nexcode.kafka.model.response;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionResponse {

	private Long id;
	private BigDecimal amount;
}
