package com.jimtough.gsk.app.springboot.mvc.controller;

import com.jimtough.gsk.lib.hellofactory.HelloFactory;
import com.jimtough.gsk.lib.worldfactory.WorldFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class MyRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyRestController.class);

	private static final String RESPONSE_HTML_TEMPLATE =
			"<html><body><h1>%s</h1><h2>%s</h2></body></html>";

	//---------------------------------------------------------------------------------------------

	@Value("${info.app.name}")
	private String appName;
	@Value("${info.app.description}")
	private String appDescription;

	@Autowired
	MyRestController() {}

	@PostConstruct
	void postConstruct() {
		LOGGER.debug("postConstruct() | INVOKED");
	}

	//---------------------------------------------------------------------------------------------

	private String generateHelloWorldResponse() {
		LOGGER.info("Someone wants to generate a hello world string");
		// What a complicated way to say "Hello, World!"  ;)
		return HelloFactory.INSTANCE.getHello() + ", " + WorldFactory.INSTANCE.getWorld() + "!";
	}

	@RequestMapping("/hello")
	public String helloHandler() {
		LOGGER.debug("Hello request received");
		return generateHelloWorldResponse();
	}

	@RequestMapping("/")
	public String contextRootHandler() {
		LOGGER.debug("request received");
		return String.format(RESPONSE_HTML_TEMPLATE, appName, appDescription);
	}

	//---------------------------------------------------------------------------------------------
	// Various endpoints that I'm using to experiment with Spring MVC features
	//---------------------------------------------------------------------------------------------

	@RequestMapping("/throw-an-exception")
	public String throwAnExceptionHandler() {
		throw new RuntimeException("I am throwing this exception on purpose. Hellooooo HTTP error code 500!");
	}

}
