@echo off
echo Creating server structure...

mkdir server\src\main\kotlin\com\rustore\server
mkdir server\src\main\resources

echo plugins { > server\build.gradle.kts
echo     kotlin("jvm") version "1.9.0" >> server\build.gradle.kts
echo     application >> server\build.gradle.kts
echo } >> server\build.gradle.kts
echo. >> server\build.gradle.kts
echo group = "com.rustore" >> server\build.gradle.kts
echo version = "1.0.0" >> server\build.gradle.kts
echo. >> server\build.gradle.kts
echo repositories { >> server\build.gradle.kts
echo     mavenCentral() >> server\build.gradle.kts
echo } >> server\build.gradle.kts
echo. >> server\build.gradle.kts
echo dependencies { >> server\build.gradle.kts
echo     implementation("io.ktor:ktor-server-core:2.3.3") >> server\build.gradle.kts
echo     implementation("io.ktor:ktor-server-netty:2.3.3") >> server\build.gradle.kts
echo     implementation("io.ktor:ktor-server-content-negotiation:2.3.3") >> server\build.gradle.kts
echo     implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.3") >> server\build.gradle.kts
echo     implementation("ch.qos.logback:logback-classic:1.4.11") >> server\build.gradle.kts
echo } >> server\build.gradle.kts
echo. >> server\build.gradle.kts
echo application { >> server\build.gradle.kts
echo     mainClass.set("com.rustore.server.ApplicationKt") >> server\build.gradle.kts
echo } >> server\build.gradle.kts

echo rootProject.name = "rustore-server" > server\settings.gradle.kts

echo package com.rustore.server > server\src\main\kotlin\com\rustore\server\Application.kt
echo. >> server\src\main\kotlin\com\rustore\server\Application.kt
echo import io.ktor.server.application.* >> server\src\main\kotlin\com\rustore\server\Application.kt
echo import io.ktor.server.engine.* >> server\src\main\kotlin\com\rustore\server\Application.kt
echo import io.ktor.server.netty.* >> server\src\main\kotlin\com\rustore\server\Application.kt
echo import io.ktor.server.response.* >> server\src\main\kotlin\com\rustore\server\Application.kt
echo import io.ktor.server.routing.* >> server\src\main\kotlin\com\rustore\server\Application.kt
echo. >> server\src\main\kotlin\com\rustore\server\Application.kt
echo fun main() { >> server\src\main\kotlin\com\rustore\server\Application.kt
echo     embeddedServer(Netty, port = 8080, host = "0.0.0.0") { >> server\src\main\kotlin\com\rustore\server\Application.kt
echo         routing { >> server\src\main\kotlin\com\rustore\server\Application.kt
echo             get("/") { call.respondText("RuStore Server is running!") } >> server\src\main\kotlin\com\rustore\server\Application.kt
echo             get("/apps") { call.respondText("Apps endpoint") } >> server\src\main\kotlin\com\rustore\server\Application.kt
echo             get("/apps/{id}") { >> server\src\main\kotlin\com\rustore\server\Application.kt
echo                 val id = call.parameters["id"] >> server\src\main\kotlin\com\rustore\server\Application.kt
echo                 call.respondText("App ID: $id") >> server\src\main\kotlin\com\rustore\server\Application.kt
echo             } >> server\src\main\kotlin\com\rustore\server\Application.kt
echo             post("/apps") { call.respondText("Upload endpoint") } >> server\src\main\kotlin\com\rustore\server\Application.kt
echo         } >> server\src\main\kotlin\com\rustore\server\Application.kt
echo     }.start(wait = true) >> server\src\main\kotlin\com\rustore\server\Application.kt
echo } >> server\src\main\kotlin\com\rustore\server\Application.kt

echo ktor { > server\src\main\resources\application.conf
echo     deployment { >> server\src\main\resources\application.conf
echo         port = 8080 >> server\src\main\resources\application.conf
echo     } >> server\src\main\resources\application.conf
echo     application { >> server\src\main\resources\application.conf
echo         modules = [com.rustore.server.ApplicationKt.main()] >> server\src\main\resources\application.conf
echo     } >> server\src\main\resources\application.conf
echo } >> server\src\main\resources\application.conf

echo Structure created! Now run: cd server && gradle wrapper
pause