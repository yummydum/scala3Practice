import myutils.{tupleToCsv, csvToTuple, csvToProduct, productToCsv}
import myutils.encoder.instances.given
import myutils.decoder.instances.given
import org.junit.Test
import org.junit.Assert._

class Test1 {
  @Test def testCsvToTuple(): Unit = {
    assertEquals(
      (42, true, "Hello"),
      csvToTuple[(Int, Boolean, String)](List("42", "true", "Hello"))
    )
  }

  @Test def testTupleToCsv(): Unit = {
    assertEquals(
      List("42", "true", "Hello"),
      tupleToCsv((42, true, "Hello"))
    )
  }

  @Test def testCsvToProduct(): Unit = {
    case class Foo(i: Int, b: Boolean, s: String)
    assertEquals(
      Foo(42, true, "Hello"),
      csvToProduct[Foo](List("42", "true", "Hello"))
    )
  }

  @Test def testProductToCsv(): Unit = {
    case class Foo(i: Int, b: Boolean, s: String)
    assertEquals(
      List("42", "true", "Hello"),
      productToCsv(Foo(42, true, "Hello"))
    )
  }
}
