name          := "htwg-scala-kakuro"
organization  := "de.htwg.se"
version       := "0.0.2"
scalaVersion  := "2.11.8"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

scalaSource in Compile := baseDirectory.value / "src/de/htwg/se/kakuro"


libraryDependencies ++= {
  val scalaTestV       = "3.0.1"
  val scalaMockV       = "3.2.2"
  Seq(
    "org.scalatest" %% "scalatest"                   % scalaTestV       % "test",
    "org.scalamock" %% "scalamock-scalatest-support" % scalaMockV       % "test"
  )
}

libraryDependencies += "junit" % "junit" % "4.8" % "test"
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.10.0"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.10.0"
libraryDependencies += "org.scala-lang" % "scala-swing" % "2.11+"
libraryDependencies += "com.google.inject" % "guice" % "3.0"
libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.5.15"