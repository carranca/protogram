plugins {
  base
  kotlin("multiplatform") version "1.3.61" apply false
}

subprojects {
  group = "com.mattprecious"
  version = "1.0-SNAPSHOT"

  repositories {
    mavenCentral()
  }
}
