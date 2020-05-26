plugins {
  base
  kotlin("multiplatform") version "1.3.61" apply false
  kotlin("jvm") version "1.3.61" apply false
  kotlin("js") version "1.3.61" apply false
  id("org.jlleitschuh.gradle.ktlint") version "9.1.1" apply false
}

subprojects {
  group = "com.mattprecious"
  version = "0.2.0"

  repositories {
    mavenCentral()
  }
}
