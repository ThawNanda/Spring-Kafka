package com.nexcode.kafka.producer;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.nexcode.kafka.model.entity.PaymentTransaction;

@Service
public class PaymentTransactionProducer {

    private static final String LOW_TOPIC = "low-topic";
    private static final String MEDIUM_TOPIC = "medium-topic";
    private static final String HIGH_TOPIC = "high-topic";

    @Autowired
    private KafkaTemplate<String, PaymentTransaction> kafkaTemplate;

    public void producePaymentTransaction(PaymentTransaction paymentTransaction) {
        String topic = getTopicByAmount(paymentTransaction.getAmount());
        kafkaTemplate.send(topic, paymentTransaction);
    }

    private String getTopicByAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("10000")) <= 0) {
            return LOW_TOPIC;
        } else if (amount.compareTo(new BigDecimal("100000")) <= 0) {
            return MEDIUM_TOPIC;
        } else {
            return HIGH_TOPIC;
        }
    }
}
