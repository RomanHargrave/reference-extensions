import SonatypeKeys._

sonatypeSettings

// Project metadata

organization := "info.hargrave"

organizationName := "Roman Hargrave"

organizationHomepage := Some(url("http://hargrave.info"))

name := "refext"

version := "0.1"

homepage := Some(url("https://github.com/romanhargrave/reference-extensions"))

startYear := Some(2015)

licenses := ("Apache Software License 2.0", url("http://www.apache.org/licenses/LICENSE-2.0.txt")) :: Nil

scmInfo := Some(
                   ScmInfo(
                              url("https://github.com/romanhargrave/reference-extensions"),
                              "git@github.com:romanhargrave/reference-extensions.git"
                          )
               )

pomExtra := {
    <developers>
        <developer>
            <id>rhargrave</id>
            <name>Roman Hargrave</name>
            <email>roman@hargrave.info</email>
        </developer>
    </developers>
}

// Project settings

scalaVersion := "2.11.4"

libraryDependencies += "org.scalatest" %% "scalatest-all" % "2.3.0-SNAP2" % "test"

// Deployment settings

publishMavenStyle := true
