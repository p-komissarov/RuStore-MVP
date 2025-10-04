package com.rustore.server 
 
import io.ktor.server.application.* 
import io.ktor.server.engine.* 
import io.ktor.server.netty.* 
import io.ktor.server.response.* 
import io.ktor.server.routing.* 
 
fun main() { 
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") { 
        routing { 
            get("/") { call.respondText("RuStore Server is running!") } 
            get("/apps") { call.respondText("Apps endpoint") } 
            get("/apps/{id}") { 
                val id = call.parameters["id"] 
                call.respondText("App ID: $id") 
            } 
            post("/apps") { call.respondText("Upload endpoint") } 
        } 
    }.start(wait = true) 
} 
