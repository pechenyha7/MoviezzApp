plugins {
	id("com.android.application")
	id("org.jetbrains.kotlin.android")
	id("kotlinx-serialization")
	id("com.google.devtools.ksp")
	id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.moviezzapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.moviezzapp"
        minSdk = 29
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
		debug {
			setupBuildConfig(AppConfig.buildConfigFields)
		}
        release {
			setupBuildConfig(AppConfig.buildConfigFields)

            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
	    sourceCompatibility = JavaVersion.VERSION_11
	    targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
	    jvmTarget = JavaVersion.VERSION_11.toString()
	    freeCompilerArgs = freeCompilerArgs + listOf(
		    "-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
		    "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
		    "-opt-in=kotlinx.coroutines.FlowPreview",
	    )
    }
    buildFeatures {
	    viewBinding = true
	    buildConfig = true
    }
    composeOptions {
	    kotlinCompilerExtensionVersion = "1.5.5"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")

	// navigation
	implementation("androidx.navigation:navigation-fragment-ktx:2.8.5")
	implementation("androidx.navigation:navigation-ui-ktx:2.8.5")

	// view binding
	implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.9")

	// recycler view
	implementation("androidx.recyclerview:recyclerview:1.3.2")
	// constraint layout
	implementation("androidx.constraintlayout:constraintlayout:2.2.0")

	// material design
	implementation("com.google.android.material:material:1.12.0")

	// image view library
	implementation("io.coil-kt.coil3:coil:3.0.4")
	implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")

	//di
	implementation("com.google.dagger:hilt-android:2.49")
	ksp("com.google.dagger:hilt-compiler:2.49")

	// api
	implementation("com.squareup.retrofit2:retrofit:2.9.0")
	implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
	implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")
	implementation("com.squareup.okhttp3:okhttp:4.10.0")
	implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")

	//database
	implementation("androidx.room:room-ktx:2.6.0")
	implementation("androidx.room:room-runtime:2.6.0")
	ksp("androidx.room:room-compiler:2.6.0")

	implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
}

fun com.android.build.api.dsl.VariantDimension.setupBuildConfig(configMap: Map<String, Any>) {
	configMap.entries.forEach { (key, value) ->
		val type = when (value) {
			is String -> "String"
			is Int -> "Integer"
			else -> throw IllegalArgumentException("Unsupported value type: $value")
		}
		when(value) {
			is String -> buildConfigField(type, key, "\"$value\"")
			else -> buildConfigField(type, key, value.toString())
		}
	}
}
