plugins {
    id("java")
}

group = "io.github.realyusufismail"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // JDA
    implementation("net.dv8tion:JDA:5.0.0-beta.20")

    // Jconfig
    implementation("io.github.realyusufismail:jconfig:1.1.2")

    testImplementation(platform("org.junit:junit-bom:5.9.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}