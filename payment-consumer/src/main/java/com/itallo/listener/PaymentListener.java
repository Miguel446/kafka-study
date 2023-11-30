package com.itallo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.itallo.model.Payment;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class PaymentListener {

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "fraud-group", containerFactory = "paymentContainerFactory")
	public void antiFraud(@Payload Payment payment) {
		log.info("Payment: {}", payment.toString());
		log.info("Validating fraud...");
		Thread.sleep(1000);

		log.info("Payment approved");
	}

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "paymentContainerFactory")
	public void pdfGenerator(@Payload Payment payment) {
		log.info("Generating PDF of Product Id {}...", payment.getId());
		Thread.sleep(2000);
		log.info("Generated PDF");
	}

	@SneakyThrows
	@KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "paymentContainerFactory")
	public void sendEmail() {
		log.info("Sending Email...");
	}

}
