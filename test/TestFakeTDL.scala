import org.apache.daffodil.tdml.Runner

import org.junit.AfterClass
import org.junit.Test

object TestFakeTDL {
  lazy val runner = Runner("/", "TestFakeTDL.tdml")

  @AfterClass def shutDown(): Unit = {
    runner.reset
  }
}

/**
 * This just makes it convenient to run all the tests one by one in a programmers IDE
 * or using 'sbt' (simple build tool).
 *
 * One can also run tests from the Daffodil Command Line Interface (CLI)
 */
class TestFakeTDL {

  import TestFakeTDL._

  @Test def test_msg_01(): Unit = { runner.runOneTest("test_msg_01") }
  @Test def test_msg_02(): Unit = { runner.runOneTest("test_msg_02") }
  @Test def test_msg_bad_01(): Unit = { runner.runOneTest("test_msg_bad_01") }
  @Test def test_msg_bad_02(): Unit = { runner.runOneTest("test_msg_bad_02") }

  @Test def test_file_01(): Unit = { runner.runOneTest("test_file_01") }
  @Test def test_file_bad_01(): Unit = { runner.runOneTest("test_file_bad_01") }

  @Test def test_msg_invalid_01(): Unit = { runner.runOneTest("test_msg_invalid_01") }

}
