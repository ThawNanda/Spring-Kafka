package com.nexcode.kafka.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexcode.kafka.dto.TransactionDto;
import com.nexcode.kafka.mapper.TransactionMapper;
import com.nexcode.kafka.model.entity.PaymentTransaction;
import com.nexcode.kafka.model.request.TransactionRequest;
import com.nexcode.kafka.model.response.TransactionResponse;
import com.nexcode.kafka.repository.PaymentTransactionRepository;
import com.nexcode.kafka.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminDashboardController {

	private final PaymentTransactionRepository transactionRepository;

	private final TransactionService transactionService;

	private final TransactionMapper transactionMapper;

	@GetMapping("/transactions")
	public List<PaymentTransaction> getAllTransactions() {
		// Retrieve all transactions from the database for manual verification
		return transactionRepository.findAll();
	}

	@PostMapping("/transactions")
	public TransactionResponse addTransaction(@RequestBody TransactionRequest request) {

		TransactionDto transaction = null;

		for (int i = 0; i < 500; i++) {

			BigDecimal result = request.getAmount().add(BigDecimal.valueOf(10000));

			request.setAmount(result);

			TransactionDto transactionDto = transactionMapper.toDto(request);

			transaction = transactionService.createTransaction(transactionDto);

		}
		return transactionMapper.toResponse(transaction);
	}

	@PostMapping("/verify-transaction")
	public void verifyTransaction(@RequestParam("transactionId") Long transactionId) {
		// Retrieve the transaction from the database based on transactionId

		Optional<PaymentTransaction> optionalTransaction = transactionRepository.findById(transactionId);
		if (optionalTransaction.isPresent()) {
			// PaymentTransaction transaction = optionalTransaction.get();
			// Perform manual verification of the transaction by updating the account
			// Add coins or perform any other necessary actions based on the verification
			// result
		} else {
			// Transaction not found
		}
	}
}
