name := "dfdl-fakeTDL"

organization := "com.owlcyberdefense"

version := "1.0.0"

// for details about DaffodilPlugin settings, see https://github.com/apache/daffodil-sbt
enablePlugins(DaffodilPlugin)

daffodilFlatLayout := true

daffodilVersion := daffodilPackageBinVersions.value.head

daffodilPackageBinVersions := Seq("3.9.0", "3.8.0", "3.7.0", "3.5.0")

daffodilPackageBinInfos := Seq(
  // schema for a single message
  DaffodilBinInfo("/fakeTDL.dfdl.xsd", root = Some("fakeTDL"), name = Some("fakeTDL")),
  // schema for a file of messages
  DaffodilBinInfo("/fakeTDL.dfdl.xsd", root = Some("fakeTDLFile"), name = Some("fakeTDLFile"))
)

// not necessary for this small schema, but for larger schemas
// where schema compilation takes a while this is important
daffodilTdmlUsesPackageBin := true
