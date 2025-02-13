package com.jimtough.gsk.lib.hellofactory;

import java.util.Objects;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Example of using a third-party library with Gradle 'implementation' dependency scope.
 * Nice dependency example - useless cache  :)
 */
class HelloCache {

	public static final HelloCache SINGLETON = new HelloCache();

	private HelloCache() {}

	enum HelloLanguage {
		ENGLISH,
		FRENCH
	}

	// In this class we use classes from the Google Guava library.
	// Use of Guava is encapsulated. No public methods expose Guava to the outside world.

	private final CacheLoader<HelloLanguage, String> loader = CacheLoader.from(
		(key) -> {
			switch (key) {
				case ENGLISH:
					return "Hello";
				case FRENCH:
					return "Bonjour";
				default:
					throw new IllegalArgumentException("Invalid enum value: " + key);
			}
		}
	);

	private final LoadingCache<HelloLanguage, String> cache = CacheBuilder.newBuilder().build(loader);

	String getHelloForLanguage(HelloLanguage helloLanguage) {
		Objects.requireNonNull(helloLanguage);
		return cache.getUnchecked(helloLanguage);
	}

}
