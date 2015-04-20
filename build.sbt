name := """playchat"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

resolvers += "Typesafe Releases" at "http://typesafe.artifactoryonline.com/typesafe"

resolvers += "pk11 repo" at "http://pk11-scratch.googlecode.com/svn/trunk"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "com.typesafe.play.plugins" %% "play-plugins-redis" % "2.3.1"
)
