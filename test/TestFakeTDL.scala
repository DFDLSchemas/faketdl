import org.apache.daffodil.tdml.Runner

import org.apache.daffodil.junit.tdml.TdmlSuite
import org.apache.daffodil.junit.tdml.TdmlTests

import org.junit.Test

object TestFakeTDL extends TdmlSuite {
  val tdmlResource = "/TestFakeTDL.tdml"
}

/**
 * This just makes it convenient to run all the tests one by one in a programmers IDE
 * or using 'sbt' (simple build tool).
 *
 * One can also run tests from the Daffodil Command Line Interface (CLI)
 */
class TestFakeTDL extends TdmlTests {
  val tdmlSuite = TestFakeTDL

  @Test def test_msg_01() = test
  @Test def test_msg_02() = test
  @Test def test_msg_bad_01() = test
  @Test def test_msg_bad_02() = test

  @Test def test_file_01() = test
  @Test def test_file_bad_01() = test

  @Test def test_msg_invalid_01() = test

}
