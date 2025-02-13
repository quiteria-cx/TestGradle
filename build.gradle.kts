
val baseGroupForSubprojects = "com.jimtough.gsk"

// Add IntelliJ IDEA plugin
apply(plugin="idea")

buildscript {
	repositories {
		mavenCentral()
	}
}

plugins {
	java

	//----------------------------------------------------------------------------------------------------------
	// This part still isn't clear to me. I need these two plugins only for the Spring Boot subproject.
	// If I try to declare them only in the subproject 'build.gradle.kts' file, then I end up with errors
	// in the build scripts inside IntelliJ.
	// With these two declarations also added here, things work again. I'm guessing the 'apply false' is
	// where the magic happens. The plugin requirements are declared here, but only 'applied' in my subproject?
	id("org.springframework.boot") version "2.2.8.RELEASE" apply false
	// REFERENCE: https://mvnrepository.com/artifact/io.spring.dependency-management/io.spring.dependency-management.gradle.plugin
	id("io.spring.dependency-management") version "1.0.9.RELEASE" apply false
	//----------------------------------------------------------------------------------------------------------
}

// The settings inside this Closure will be applied to the root project and each subproject.
// The delegate object type is 'Project'.
allprojects {
	apply(plugin = "java")

	version = "0.1.0-SNAPSHOT"

	// Pull all dependencies from Maven Central
	repositories {
		mavenCentral()
	}
}

// The settings inside this Closure will be applied to each subproject, but NOT the root project.
// The delegate object type is 'Project'.
subprojects {
	configure<JavaPluginExtension> {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	// Used to create a 'sources' JAR file
	tasks.register<Jar>("sourcesJar") {
		archiveClassifier.set("sources")
		from(sourceSets.main.get().allJava)
	}

	dependencies {
		implementation("org.slf4j:slf4j-api") {
			version {
				// Allow any 1.7.x release
				strictly("[1.7, 1.8[")
				// Default to 1.7.22 if nothing else depends on this artifact and requests a different version
				prefer("1.7.30")
			}
		}
		// Use JUnit Jupiter API for testing
		testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
		// Use JUnit Jupiter Engine for testing
		testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
		// Use Logback when running unit tests
		testRuntimeOnly("ch.qos.logback:logback-classic") {
			version {
				// Always use a 1.2.x release of Logback
				strictly("[1.2, 1.3[")
			}
		}
	}

	tasks {
		jar {
			manifest {
				attributes(
					mapOf(
							"hi" to "mom",
							"Implementation-Title" to project.name,
							"Implementation-Version" to project.version
					)
				)
			}
		}
		test {
			// Use JUnit 5
			useJUnitPlatform()
		}
	}

}

//-------------------------------------------------------------------------------------------------
// In this section, project-specific configuration is applied to subprojects by name.
// NOTE: Must use colon-prefix notation when referring to a subproject by name.
//       In the case where subprojects are nested more than one level deep (not done in this example),
//       one must separate each nesting level with a colon like ':childprj:grandchildprj'.
// NOTE: Another alternative is to put subproject-specific configuration into the 'build.gradle.kts'
//       file for each subproject. I don't know if there are limitations to either approach,
//       or if this is simply a matter of developer preference.
project(":library-hello-factory") {
	group = "${baseGroupForSubprojects}.hellofactory"

	// This plugin is needed for library projects that produce a single JAR as output
	apply<JavaLibraryPlugin>()

	dependencies {
		// This library uses Google Guava internally, but is it not exposed in any public API.
		// Example of 'implementation' dependency scope.
		implementation("com.google.guava:guava:27.1-jre")
	}
}
project(":library-world-factory") {
	group = "${baseGroupForSubprojects}.worldfactory"

	// This plugin is needed for library projects that produce a single JAR as output
	apply<JavaLibraryPlugin>()
}
//-------------------------------------------------------------------------------------------------



// Applies to the entire build
tasks {
	wrapper {
		gradleVersion = "6.5.1"
		distributionType = Wrapper.DistributionType.ALL
	}
}

