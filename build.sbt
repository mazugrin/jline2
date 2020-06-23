val jansi = "org.fusesource.jansi" % "jansi" % "1.12"
val junit = "com.novocode" % "junit-interface" % "0.11"
val easymock = "org.easymock" % "easymock" % "3.3.1"
def powermockVersion = "1.6.2"
val powermockJunit4 = "org.powermock" % "powermock-module-junit4" % powermockVersion
val powermockEasymock = "org.powermock" % "powermock-api-easymock" % powermockVersion

lazy val root = (project in file("."))
  .enablePlugins(GitVersioning)
  .settings(nocomma {
    name := "jline"
    git.baseVersion := "2.14.7-sbt"
    libraryDependencies += jansi
    libraryDependencies ++= Seq(
      junit % Test,
      easymock % Test,
      easymock % Test,
      powermockJunit4 % Test,
      powermockEasymock % Test
    )
    Compile / javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
    Compile / doc / javacOptions := Seq("-source", "1.6")
    Test / parallelExecution := false
  })
