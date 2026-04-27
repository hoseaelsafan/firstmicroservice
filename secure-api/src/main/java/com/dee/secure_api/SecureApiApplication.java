package com.dee.secure_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SecureApiApplication {

//	private static final Logger log = LoggerFactory.getLogger(SecureApiApplication.class);
//
//	@EventListener(ApplicationReadyEvent.class)
//	public void onReady() {
//		log.info("🚀 Application is fully ready");
//	}

	public static void main(String[] args) {
		SpringApplication.run(SecureApiApplication.class, args);
	}

}
