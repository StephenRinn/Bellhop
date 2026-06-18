ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.8"

lazy val root = (project in file("."))
  .settings(
    name := "Bellhop",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-effect" % "3.6.0",
      "org.typelevel" %% "otel4s-oteljava" % "1.0.0",
      "org.typelevel" %% "otel4s-oteljava-context-storage" % "1.0.0",
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.typelevel" %% "munit-cats-effect" % "2.0.0" % Test,
    )
  )