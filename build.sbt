name := "dfdl-fakeTDL"

organization := "com.owlcyberdefense"

version := "1.0.0-SNAPSHOT"

// for details about DaffodilPlugin settings, see https://github.com/apache/daffodil-sbt
enablePlugins(DaffodilPlugin)

daffodilFlatLayout := true

daffodilVersion := daffodilPackageBinVersions.value.head

daffodilPackageBinVersions := Seq("3.5.0", "3.8.0", "3.7.0")
