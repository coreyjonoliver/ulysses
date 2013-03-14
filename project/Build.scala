package ulysses

import sbt._
import Keys._

object UlyssesBuild extends Build {

  lazy val standardSettings = Defaults.defaultSettings ++ Seq(
    version := "0.1",
    scalaVersion := "2.9.2",
    organization := "org.coreyoliver",

    parallelExecution in Test := false,

    scalacOptions ++= Seq("-unchecked", "-deprecation"),

    publishTo <<= version { (v: String) =>
      val nexus = "https://oss.sonatype.org/"
      if (v.trim.endsWith("SNAPSHOT"))
        Some("sonatype-snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("sonatype-releases" at nexus + "service/local/stagins/deploy/maven2")
    },

    publishMavenStyle := true,

    publishArtifact in Test := false,

    pomIncludeRepository := { _ => false },

    pomExtra := (
      <url>https://github.com/twitter/scalding</url>
      <licenses>
        <license>
          <name>Apache 2</name>
          <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
          <distribution>repo</distribution>
          <comments>A business-friendly OSS license</comments>
        </license>
      </licenses>
      <scm>
        <url>git@github.com:coreyjonoliver/ulysses.git</url>
        <connection>scm:git@github.com:coreyjonoliver/ulysses.git</connection>
      </scm>
      <developers>
        <developer>
          <id>coreyoliver</id>
          <name>Corey Oliver</name>
          <url>http://www.coreyoliver.org</url>
        </developer>
      </developers>)
  )

  lazy val ulysses = Project(
    id = "ulysses",
    base = file("."),
    settings = standardSettings
  ).settings(
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.0.M5b",
      "org.scalaz" %% "scalaz-core" % "7.0.0-M8",
      "org.scalacheck" %% "scalacheck" % "1.10.0"
    ),
    name := "ulysses"
  )
}
