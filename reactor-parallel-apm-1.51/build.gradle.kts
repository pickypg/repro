plugins {
    id("com.zoltu.application-agent") version "1.0.8"
    application
    java
}

group = "org.pickypg"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    agent("co.elastic.apm:elastic-apm-agent:1.51.0")
    implementation("co.elastic.apm:apm-agent-api:1.51.0")
    implementation("io.projectreactor:reactor-core:3.5.11")
}

application {
    mainClass = "org.pickypg.repro.reactor_parallel_apm_1_51.Main"
}
