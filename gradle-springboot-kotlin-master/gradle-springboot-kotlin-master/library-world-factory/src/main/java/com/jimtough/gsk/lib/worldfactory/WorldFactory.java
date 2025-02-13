package com.jimtough.gsk.lib.worldfactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldFactory {

	private static final Logger LOGGER = LoggerFactory.getLogger(WorldFactory.class);

	private static final String WORLD_STRING = "World";

	public static final WorldFactory INSTANCE = new WorldFactory();

	private WorldFactory() {}

	public String getWorld() {
		LOGGER.info("Someone has requested a world");
		return WORLD_STRING;
	}

}
