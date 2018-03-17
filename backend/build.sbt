name := "backend"

publishArtifact in (Test, packageBin) := true

addArtifact(artifact in (IntegrationTest, packageBin), packageBin in IntegrationTest).settings

