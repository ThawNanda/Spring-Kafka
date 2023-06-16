package com.nexcode.kafka.service;

import com.nexcode.kafka.dto.TransactionDto;

public interface TransactionService {

	TransactionDto createTransaction(TransactionDto transactionDto);

}
