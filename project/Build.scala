import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._

object Build extends Build {

  lazy val scalatrain = Project(
    "scalatrain",
    file("."),
    settings = 
      Defaults.defaultSettings ++
      scalariformSettings ++
      Seq(
        organization := "com.typesafe.training",
        version := "2.0.0",
        scalaVersion := "2.10.0",
        scalacOptions ++= Seq(
          "-unchecked",
          "-deprecation",
          "-Xlint",
          "-language:_",
          "-target:jvm-1.6",
          "-encoding", "UTF-8"
        ),
        libraryDependencies ++= Seq(
          "org.scalatest" %% "scalatest" % "2.0.M5b" % "test",
          "org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
        ),
        retrieveManaged := true,
        initialCommands in console := "import com.typesafe.training.scalatrain._,misc._",
        initialCommands in (Test, console) <<= (initialCommands in console)(_ + ",TestData._")
      )
  )
}
