plugins {
	id("com.android.application") version "8.2.0" apply false
	id("org.jetbrains.kotlin.android") version "1.9.20" apply false
	id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
	id("com.google.dagger.hilt.android") version "2.49" apply false
}

buildscript {
	repositories {
		gradlePluginPortal()
		google()
		mavenCentral()

	}
	dependencies {
		classpath("com.android.tools.build:gradle:8.7.3")
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
		classpath("org.jetbrains.kotlin:kotlin-serialization:1.9.20")
		classpath("com.google.dagger:hilt-android-gradle-plugin:2.49")
	}
}
