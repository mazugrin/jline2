organization := "org.scala-sbt.jline"

name := "jline"

autoScalaLibrary := false

crossPaths := false

libraryDependencies += "org.fusesource.jansi" % "jansi" % "1.12"

/*
 * This project uses java 6 doc syntax that isn't parseable by 1.7 which makes it
 * impossible to generate docs even with sbt 0.13.
 */
Compile / doc := file("/dev/null")

version := "2.14.7-SNAPSHOT"

Compile / javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
