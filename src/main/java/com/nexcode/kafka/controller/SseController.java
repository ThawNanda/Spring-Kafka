package com.nexcode.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.nexcode.kafka.service.EmitterService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class SseController {

	private final EmitterService emitterService;

	@GetMapping("/subscribe/{topic}")
	public SseEmitter subscribe(@PathVariable String topic) {
		log.info("subscribing...");
		long tokenTimeOut = 3 * 60 * 60 * 1000L;

		SseEmitter emitter = new SseEmitter(tokenTimeOut);
		emitterService.addEmitter(topic, emitter);

		log.info("subscribed");

		return emitter;
	}

}