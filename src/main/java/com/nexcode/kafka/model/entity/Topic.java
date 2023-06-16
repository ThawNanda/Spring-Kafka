package com.nexcode.kafka.model.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Topic {
	private final String name;
	private final List<SseEmitter> subscribers;

	public Topic(String name) {
		this.name = name;
		this.subscribers = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void addSubscriber(SseEmitter subscriber) {
		subscribers.add(subscriber);
	}

	public void removeSubscriber(SseEmitter subscriber) {
		subscribers.remove(subscriber);
	}

	public List<SseEmitter> getSubscribers() {
		return subscribers;
	}
}
