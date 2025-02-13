package com.jimtough.gsk.lib.hellofactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HelloFactoryTest {

	@Test
	public void testGetHello() {
		assertEquals("Hello", HelloFactory.INSTANCE.getHello());
	}

}
