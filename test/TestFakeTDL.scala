import org.apache.daffodil.tdml.Runner

import org.junit.AfterClass
import org.junit.Test

object TestFakeTDL {
  lazy val runner = Runner("/", "TestFakeTDL.tdml")

  @AfterClass def shutDown: Unit = {
    runner.reset
  }
}

class TestFakeTDL {

  import TestFakeTDL._

  @Test def test_fakeTDL_01(): Unit = { runner.runOneTest("test_fakeTDL_01") }
  @Test def test_fakeTDLFile_01(): Unit = { runner.runOneTest("test_fakeTDLFile_01") }
}
