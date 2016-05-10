import scala.util.Try

name := "SparkByExamples"

version := "1.0"

val buildNumber = Try(sys.env("BUILD_NUMBER")).getOrElse("0000")

scalaVersion := "2.10.6"

scalacOptions := Seq("-deprecation", "-unchecked", "-feature")

mainClass in Compile := Some("rojosam.ExampleApp")

resolvers += "gphat" at "https://raw.github.com/gphat/mvn-repo/master/releases/"

libraryDependencies ++=  Seq(
  "org.apache.spark"     %% "spark-core"                  % "1.6.1"           % "provided",
  "org.apache.spark"     %% "spark-sql"                   % "1.6.1"           % "provided",
  "com.holdenkarau"      %% "spark-testing-base"          % "1.6.1_0.3.3",
  "org.apache.thrift"    %  "libthrift"                   % "0.8.0",
  "com.twitter"          %% "scrooge-core"                % "4.6.0",
  "com.twitter"          %% "finagle-thrift"              % "6.34.0"
)

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.**" -> "shadeio.@1").inAll
)

assemblyJarName in assembly := s"${name.value}-${version.value}-${buildNumber.padTo(4,"0").mkString("")}.jar"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case _ => MergeStrategy.first
}

coverageEnabled.in(Test, test) := true

test in assembly := {}
