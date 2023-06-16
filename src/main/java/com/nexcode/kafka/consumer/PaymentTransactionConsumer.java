package com.nexcode.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.nexcode.kafka.model.entity.PaymentTransaction;
import com.nexcode.kafka.repository.PaymentTransactionRepository;
import com.nexcode.kafka.service.EmitterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//@Slf4j
public class PaymentTransactionConsumer {

	private final PaymentTransactionRepository transactionRepository;

	private final EmitterService emitterService;

	@KafkaListener(topics = "low-topic", groupId = "consumer-group")
	public void consumeLowTopic(@Payload PaymentTransaction paymentTransaction) {
		PaymentTransaction transaction = processTransaction(paymentTransaction);
		emitterService.send("low-topic", transaction.getAmount());
	}

	@KafkaListener(topics = "medium-topic", groupId = "consumer-group")
	public void consumeMediumTopic(@Payload PaymentTransaction paymentTransaction) {
		PaymentTransaction transaction = processTransaction(paymentTransaction);
		emitterService.send("medium-topic", transaction.getAmount());
	}

	@KafkaListener(topics = "high-topic", groupId = "consumer-group")
	public void consumeHighTopic(@Payload PaymentTransaction paymentTransaction) {
		PaymentTransaction transaction = processTransaction(paymentTransaction);
		emitterService.send("high-topic", transaction.getAmount());
	}

	private PaymentTransaction processTransaction(PaymentTransaction paymentTransaction) {
		return transactionRepository.save(paymentTransaction);
	}
}
