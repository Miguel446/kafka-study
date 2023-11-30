package com.itallo.exception;

import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ErrorCustomHandler implements KafkaListenerErrorHandler {

	@Override
	public Object handleError(Message<?> message, ListenerExecutionFailedException exception) {
		log.warn("EXCEPTION HANDLER!");
		log.warn("Payload: {}", message.getPayload());
		log.warn("Headers: {}", message.getHeaders());
		log.warn("Offset: {}", message.getHeaders().get("kafka_offset"));
		log.info("Exception: {}", exception.getMessage());
		return null;
	}

}
