name := "play"

version := "1.0"

lazy val `play` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.5"

libraryDependencies ++= Seq(jdbc, anorm, cache, ws,
  "mysql" % "mysql-connector-java" % "5.1.35",
  "com.typesafe.play" %% "play-slick" % "0.8.0",
  "com.typesafe.slick" %% "slick" % "2.1.0",
  "io.strongtyped" %% "active-slick" % "0.2.2",
  "org.slf4j" % "slf4j-nop" % "1.6.4")

unmanagedResourceDirectories in Test <+= baseDirectory(_ / "target/web/public/test")