#!/bin/bash

echo "Creating Ktor server structure..."

# Create main directory structure
mkdir -p server/src/main/kotlin/com/rustore/server/plugins
mkdir -p server/src/main/kotlin/com/rustore/server/routes
mkdir -p server/src/main/resources
mkdir -p server/gradle/wrapper

# Create configuration files
cat > server/build.gradle.kts << 'EOL'
plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.rustore"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core:2.3.3")
    implementation("io.ktor:ktor-server-netty:2.3.3")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3")
    implementation("io.ktor:ktor-server-cors:2.3.3")
    implementation("ch.qos.logback:logback-classic:1.4.11")
}

application {
    mainClass.set("com.rustore.server.ApplicationKt")
}
EOL

cat > server/settings.gradle.kts << 'EOL'
rootProject.name = "rustore-server"
EOL

cat > server/src/main/kotlin/com/rustore/server/Application.kt << 'EOL'
package com.rustore.server

import com.rustore.server.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureRouting()
}
EOL

cat > server/src/main/kotlin/com/rustore/server/plugins/ConfigureRouting.kt << 'EOL'
package com.rustore.server.plugins

import com.rustore.server.routes.appsRouting
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("RuStore Server is running!")
        }
        
        appsRouting()
    }
}

fun Application.configureSerialization() {
    // Serialization configuration will be added later
}
EOL

cat > server/src/main/kotlin/com/rustore/server/routes/AppsRouting.kt << 'EOL'
package com.rustore.server.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.appsRouting() {
    route("/apps") {
        get {
            call.respondText("Apps endpoint - data will be added later")
        }
        
        get("/{id}") {
            val id = call.parameters["id"]
            call.respondText("App details for ID: $id - data will be added later")
        }
        
        post {
            call.respondText("App upload endpoint - functionality will be added later")
        }
    }
}
EOL

cat > server/src/main/resources/application.conf << 'EOL'
ktor {
    deployment {
        port = 8080
        port = ${?PORT}
    }
    application {
        modules = [ com.rustore.server.ApplicationKt.module ]
    }
}
EOL

cat > server/gradle/wrapper/gradle-wrapper.properties << 'EOL'
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2.1-bin.zip
networkTimeout=10000
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOL

# Create empty files for Gradle wrapper
touch server/gradle/wrapper/gradle-wrapper.jar
touch server/gradlew
touch server/gradlew.bat

echo "Server structure created successfully!"
echo "Run the server with: cd server && ./gradlew run"
