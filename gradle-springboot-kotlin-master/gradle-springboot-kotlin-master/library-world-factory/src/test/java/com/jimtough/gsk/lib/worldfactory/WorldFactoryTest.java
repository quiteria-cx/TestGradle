package com.jimtough.gsk.lib.worldfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class WorldFactoryTest {

	@Test
	void testGetWorld() {
		assertEquals("World", WorldFactory.INSTANCE.getWorld());
	}

}
