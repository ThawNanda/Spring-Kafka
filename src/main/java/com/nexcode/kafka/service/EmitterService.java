package com.nexcode.kafka.service;

import java.math.BigDecimal;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface EmitterService {

	void addEmitter(Long id, SseEmitter sseEmitter);

	void addEmitter(String topic, SseEmitter emitter);

	void send(String string, BigDecimal amount);

}
