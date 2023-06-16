package com.nexcode.kafka.service;

import org.springframework.stereotype.Service;

import com.nexcode.kafka.dto.TransactionDto;
import com.nexcode.kafka.model.entity.PaymentTransaction;
import com.nexcode.kafka.producer.PaymentTransactionProducer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final PaymentTransactionProducer producer;

	@Override
	public TransactionDto createTransaction(TransactionDto transactionDto) {

		PaymentTransaction transaction = new PaymentTransaction();

		transaction.setAmount(transactionDto.getAmount());

		producer.producePaymentTransaction(transaction);

		return transactionDto;
	}

}
