package com.nexcode.kafka.mapper;

import com.nexcode.kafka.dto.TransactionDto;
import com.nexcode.kafka.model.request.TransactionRequest;
import com.nexcode.kafka.model.response.TransactionResponse;

public interface TransactionMapper {

	TransactionDto toDto(TransactionRequest request);

	TransactionResponse toResponse(TransactionDto transactionDto);
	
}
