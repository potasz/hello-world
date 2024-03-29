import sbt._
import Keys._

object MainBuild extends Build {

    lazy val root = Project(id = "scala_misc_sbt",
                            base = file("."),
                            settings = Project.defaultSettings ++ Seq(
                              libraryDependencies ++= Seq(
                                "org.scalatest" %% "scalatest" % "1.6.1" % "test",
								"scala_react" %% "scala_react" % "0.1",
                                "com.google.guava" % "guava" % "10.0.1"
                              )
                            ))
}