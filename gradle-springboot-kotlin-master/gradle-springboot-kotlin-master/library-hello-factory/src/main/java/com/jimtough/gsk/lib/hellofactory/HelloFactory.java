package com.jimtough.gsk.lib.hellofactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloFactory.class);

	public static final HelloFactory INSTANCE = new HelloFactory();

	private HelloFactory() {}

	public String getHello() {
		LOGGER.info("Someone has reqeusted a hello");
		return HelloCache.SINGLETON.getHelloForLanguage(HelloCache.HelloLanguage.ENGLISH);
	}

}
