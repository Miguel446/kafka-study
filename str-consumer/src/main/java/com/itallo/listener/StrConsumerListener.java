package com.itallo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.itallo.annotation.StrConsumerCustomListener;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class StrConsumerListener {

	@StrConsumerCustomListener(groupId = "group-1")
	public void listener(String message) {
		log.info("Mensagem recebida: {}", message);
	}

	@StrConsumerCustomListener(groupId = "group-1")
	public void log(String message) {
		log.info("LOG: {}", message);
	}

	@KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
	public void history(String message) {
		log.info("HISTORY: {}", message);
	}

}
