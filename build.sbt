name := "dfdl-fakeTDL"

organization := "com.owlcyberdefense"

version := "1.0.0-SNAPSHOT"

// for details about DaffodilPlugin settings, see https://github.com/apache/daffodil-sbt
enablePlugins(DaffodilPlugin)

daffodilFlatLayout := true

daffodilVersion := daffodilPackageBinVersions.value.head

libraryDependencies ++= Seq(
  "com.owlcyberdefense" % "dfdl-test-harness" % "0.0.1",
)

daffodilPackageBinVersions := Seq("3.9.0", "3.8.0", "3.7.0", "3.5.0", "3.4.0", "3.3.0")

daffodilPackageBinInfos := Seq(
  // schema for a single message
  DaffodilBinInfo("/fakeTDL.dfdl.xsd", root = Some("fakeTDL"), name = Some("fakeTDL")),
  // schema for a file of messages with metadata
  DaffodilBinInfo("/fakeTDLWithMetadata.dfdl.xsd", root = Some("fakeTDLWithMetadata"), name = Some("fakeTDLWithMetadata")),
  // schema for a file of messages
  DaffodilBinInfo("/fakeTDL.dfdl.xsd", root = Some("fakeTDLFile"), name = Some("fakeTDLFile"))
)

// not necessary for this small schema, but for larger schemas
// where schema compilation takes a while this is important
daffodilTdmlUsesPackageBin := true
