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
    implementation("ch.qos.logback:logback-classic:1.4.11") 
} 
 
application { 
    mainClass.set("com.rustore.server.ApplicationKt") 
} 
