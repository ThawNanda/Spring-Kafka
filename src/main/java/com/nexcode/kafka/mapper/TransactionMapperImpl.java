package com.nexcode.kafka.mapper;

import org.springframework.stereotype.Component;

import com.nexcode.kafka.dto.TransactionDto;
import com.nexcode.kafka.model.request.TransactionRequest;
import com.nexcode.kafka.model.response.TransactionResponse;

@Component
public class TransactionMapperImpl implements TransactionMapper {

	@Override
	public TransactionDto toDto(TransactionRequest request) {

		TransactionDto transactionDto = new TransactionDto();
		transactionDto.setAmount(request.getAmount());

		return transactionDto;
	}

	@Override
	public TransactionResponse toResponse(TransactionDto transactionDto) {

		TransactionResponse response = new TransactionResponse();
		response.setId(transactionDto.getId());
		response.setAmount(transactionDto.getAmount());

		return response;
	}

}
