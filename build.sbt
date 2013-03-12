name := "ulysses"

organization := "org.coreyoliver"

version := "0.1"

scalaVersion := "2.9.2"

resolvers ++= Seq(
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0.M5b",
  "org.scalaz" %% "scalaz-core" % "7.0.0-M8",
  "org.scalacheck" %% "scalacheck" % "1.10.0"
)
