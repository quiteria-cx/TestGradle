package com.jimtough.gsk.lib.hellofactory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class HelloCacheTest {

	@Test
	void testGetHelloForLanguage_nominal() {
		assertEquals("Hello", HelloCache.SINGLETON.getHelloForLanguage(HelloCache.HelloLanguage.ENGLISH));
		assertEquals("Bonjour", HelloCache.SINGLETON.getHelloForLanguage(HelloCache.HelloLanguage.FRENCH));
	}

	@Test
	void testGetHelloForLanguageWhenKeyIsNull() {
		assertThrows(NullPointerException.class, ()->HelloCache.SINGLETON.getHelloForLanguage(null));
	}

}
