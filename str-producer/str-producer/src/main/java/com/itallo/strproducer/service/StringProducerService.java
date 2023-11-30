package com.itallo.strproducer.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class StringProducerService {

	private final KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		kafkaTemplate.send("str-topic", message).whenComplete((result, ex) -> {
			if (ex == null) {
				log.info("Mensagem enviada com sucesso: {}", message);
				log.info("Partition: {}, Offset: {}", result.getRecordMetadata().partition(),
						result.getRecordMetadata().offset());
			} else {
				log.error("Erro ao enviar mensagem");
			}
		});
	}

}
