package com.itallo.service;

import java.io.Serializable;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.itallo.model.Payment;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@Service
public class PaymentService {

	private final KafkaTemplate<String, Serializable> kafkaTemplate;

	@SneakyThrows
	public void sendPayment(Payment payment) {
		log.info("Payment received: {}", payment);
		// Thread.sleep(1000);

		log.info("Sending Payment");
		kafkaTemplate.send("payment-topic", payment);
	}

}
