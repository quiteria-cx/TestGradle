package com.jimtough.gsk.app.springboot;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HelloWorldSpringBootApp {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldSpringBootApp.class);

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldSpringBootApp.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ApplicationContext ctx) {
		// Lambda expression implements ApplicationRunner
		return (aa) -> LOGGER.info("My ApplicationRunner was invoked | supplied args: {}", Arrays.toString(aa.getSourceArgs()));
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		// Lambda expression implements CommandLineRunner
		return (args) -> LOGGER.info("My CommandLineRunner was invoked | supplied args: {}", Arrays.toString(args));
	}

}
