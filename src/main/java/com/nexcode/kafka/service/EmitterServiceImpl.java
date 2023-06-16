package com.nexcode.kafka.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.nexcode.kafka.model.entity.Topic;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmitterServiceImpl implements EmitterService {

	private Map<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

	private final Map<String, Topic> topics = new ConcurrentHashMap<>();

	@Override
	public void addEmitter(Long userId, SseEmitter emitter) {
		emitter.onCompletion(() -> emitters.remove(userId, emitter));
		emitter.onTimeout(() -> emitters.remove(userId, emitter));
		emitter.onError(e -> {
			log.error("Create Specific-SseEmitter exception", e);
			emitters.remove(userId, e);
		});

		emitters.put(userId, emitter);
	}

	@Override
	public void addEmitter(String topic, SseEmitter emitter) {

		Topic topicObj = topics.computeIfAbsent(topic, Topic::new);
		topicObj.addSubscriber(emitter);

		// Remove the emitter when the client disconnects
		emitter.onCompletion(() -> topicObj.removeSubscriber(emitter));
		emitter.onTimeout(() -> topicObj.removeSubscriber(emitter));

	}

	@Override
	public void send(String topic, BigDecimal amount) {
		Topic topicObj = topics.get(topic);
		if (topicObj != null) {
			List<SseEmitter> deadEmitters = new ArrayList<>();
			for (SseEmitter emitter : topicObj.getSubscribers()) {
				try {
					emitter.send(amount);
				} catch (IOException e) {
					// Client connection is closed, remove the emitter
					deadEmitters.add(emitter);
				}
			}
			topicObj.getSubscribers().removeAll(deadEmitters);
		}

	}

}
