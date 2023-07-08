plugins {
  kotlin("jvm") version "1.8.21"
  kotlin("plugin.serialization") version "1.8.21"
}

group = "io.saltybyte"
version = "0.1.0"

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
//    compileOnly("net.portswigger.burp.extensions:montoya-api:2023.5")
  compileOnly("net.portswigger.burp.extender:burp-extender-api:2.3")
  testImplementation(kotlin("test"))
}

tasks.test {
  useJUnitPlatform()
}

kotlin {
  jvmToolchain(11)
}

tasks {
  register("fatJar", Jar::class.java) {
    archiveClassifier.set("all")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    val sourcesMain = sourceSets.main.get()
    from(sourcesMain.output)
  }
}
