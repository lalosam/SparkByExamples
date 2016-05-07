name := "SparkByExamples"

version := "1.0"

scalaVersion := "2.10.6"

scalacOptions := Seq("-deprecation", "-unchecked", "-feature")

mainClass in Compile := Some("rojosam.ExampleApp")

libraryDependencies ++=  Seq(
  "org.apache.spark" %% "spark-core" % "1.6.1" % "provided",
  "org.apache.spark" %% "spark-sql" % "1.6.1" % "provided",
  "com.datastax.spark" %% "spark-cassandra-connector" % "1.5.0",
  //"com.datastax.spark" %% "spark-cassandra-connector-java" % "1.5.0",
  "com.holdenkarau" %% "spark-testing-base" % "1.6.1_0.3.3"

)

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.**" -> "shadeio.@1").inAll
)

assemblyJarName in assembly := s"${name.value}-${version.value}.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}


coverageEnabled := true
