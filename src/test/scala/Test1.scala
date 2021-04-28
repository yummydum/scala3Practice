import myutils.func.tupleToCsv
import myutils.encoder.instances.given
import org.junit.Test
import org.junit.Assert._

class Test1 {
  @Test def testCaseClass2CSV(): Unit = {
    assertEquals(
      tupleToCsv((42, true, "Hello")),
      List("42", "true", "Hello")
    )
  }
}
