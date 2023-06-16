package com.nexcode.kafka.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDto {

	private Long id;
	private BigDecimal amount;
}
