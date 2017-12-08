name := """api-ember"""
organization := "test"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

PlayKeys.externalizeResources := false

scalaVersion := "2.12.2"

libraryDependencies += guice

libraryDependencies ++= Seq(
  jdbc,
  evolutions,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "org.postgresql" % "postgresql" % "9.4.1208.jre7",
  cache,
  "org.scala-lang" % "scala-library" % "2.12.2",
  "org.mindrot" % "jbcrypt" % "0.3m",
  javaWs
)
