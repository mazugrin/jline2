enablePlugins(GitVersioning)

git.baseVersion := "2.14.7-sbt"

organization := "org.scala-sbt.jline"

name := "jline"

homepage := Some(url("https://github.com/sbt/jline2"))

description := "patched jline2 for sbt"

val license = 
  "https://github.com/sbt/jline2/blob/master/src/main/resources/META-INF/LICENSE"
licenses := List("Apache-2.0" -> url(license))

scmInfo := Some(ScmInfo(url("https://github.com/sbt/jline2"), "git@github.com:sbt/jline2.git"))

developers := List(
  Developer("eed3si9n", "Eugene Yokota", "@eed3si9n", url("https://github.com/eed3si9n")),
  Developer("eatkins", "Ethan Atkins", "@eatkins", url("https://www.ethanatkins.com")),
)

autoScalaLibrary := false

crossPaths := false

libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.12"

/*
 * This project uses java 6 doc syntax that isn't parseable by 1.7 which makes it
 * impossible to generate docs even with sbt 0.13.
 */
Compile / doc := file("/dev/null")

Compile / javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
