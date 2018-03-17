name := "my-sbt-scoverage-problems"

organization in ThisBuild := "myorg"

//
// Scala & Compiler options
//
scalaVersion in ThisBuild := "2.11.6"

scalacOptions in ThisBuild := Seq(
  "-feature",
  "-deprecation",
  "-unchecked"
)

lazy val useProjectScalaVersion = Seq(
  ivyScala := ivyScala.value map { _.copy(overrideScalaVersion = true) }
)

//
// Project & Module setup
//
lazy val IntegrationTestWithUnitTestAsDep = config("it") extend Test

def baseProject(
  id: String,
  base: File,
  settings: Seq[Def.Setting[_]] = Seq.empty,
  dependencies: Seq[ModuleID] = Seq.empty
): Project =
  Project(id = id, base = base, settings = settings)
    .configs(IntegrationTestWithUnitTestAsDep)
    .settings(useProjectScalaVersion)
    .settings(Defaults.itSettings : _*)
    .settings(libraryDependencies ++= dependencies)
    .enablePlugins(ScoverageSbtPlugin)
    .settings(
      coverageExcludedPackages := ".*module.*;",
      coverageExcludedFiles := ".*buildinfo.BuildInfo",
      coverageOutputDebug := true
    )
    .enablePlugins(BuildInfoPlugin)
    .settings(
      buildInfoKeys := Seq[BuildInfoKey](version)
    )

lazy val backend = baseProject("backend", file("backend"))

//
// Other settings
//
testForkedParallel in ThisBuild := false
fork in ThisBuild := false
parallelExecution in ThisBuild := false

// Make sure the root isn't published
publishArtifact := false

// enables integration tests
Defaults.itSettings
