import myutils.{tupleToCsv, csvToTuple}
import myutils.encoder.instances.given
import myutils.decoder.instances.given
import org.junit.Test
import org.junit.Assert._

class Test1 {
  @Test def testCaseClassToCSV(): Unit = {
    assertEquals(
      List("42", "true", "Hello"),
      tupleToCsv((42, true, "Hello"))
    )
  }

  @Test def testcsvToTuple(): Unit = {
    assertEquals(
      (42, true, "Hello"),
      csvToTuple[(Int, Boolean, String)](List("42", "true", "Hello"))
    )
  }
}
