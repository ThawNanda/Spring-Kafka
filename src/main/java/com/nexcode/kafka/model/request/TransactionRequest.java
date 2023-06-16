package com.nexcode.kafka.model.request;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequest {

	private BigDecimal amount;
}
