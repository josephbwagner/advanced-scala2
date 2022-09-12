ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "advanced-scala2",
    idePackagePrefix := Some("org.joseph.advscala2")
  )
