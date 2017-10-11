
name := "SparkTPApp1"

version := "1.0"

scalaVersion := "2.10.5"

libraryDependencies += "org.apache.spark" %% "spark-core" % "1.6.0"

javacOptions ++= Seq("-source", "1.7", "-target", "1.7") 
