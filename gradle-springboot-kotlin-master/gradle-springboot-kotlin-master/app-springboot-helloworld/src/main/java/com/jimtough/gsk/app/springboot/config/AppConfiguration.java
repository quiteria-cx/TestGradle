package com.jimtough.gsk.app.springboot.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfiguration.class);

	@PostConstruct
	void postConstruct() {
		LOGGER.debug("postConstruct() | INVOKED");
	}

}
