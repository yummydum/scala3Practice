import myutils.{tupleToCsv, csvToTuple}
import myutils.encoder.instances.given
import myutils.decoder.instances.given

import java.io.File
import scala.util.{Failure, Success, Using}

import com.github.tototoshi.csv._

case class Airline(
    airline: String,
    avail_seat_km_per_week: Long,
    incidents_85_99: Int,
    fatal_accidents_85_99: Int,
    fatalities_85_99: Int,
    incidents_00_14: Int,
    fatal_accidents_00_14: Int,
    fatalities_00_14: Int
)

def parse(row: Seq[String]) = {
  Airline(
    row(0),
    row(1).toLong,
    row(2).toInt,
    row(3).toInt,
    row(4).toInt,
    row(5).toInt,
    row(6).toInt,
    row(7).toInt
  )
}

def process(data: Iterator[Seq[String]]): Iterator[List[String]] = {
  data
    .map(parse)
    .filter(x => x.avail_seat_km_per_week <= 400000000)
    .map(x => tupleToCsv(Tuple.fromProductTyped(x)))
}

def readTransformWrite[A](
    infile: String,
    outfile: String,
    func: Iterator[Seq[String]] => Iterator[List[String]]
) = {
  Using.Manager { use =>
    val reader = use(CSVReader.open(File(infile)))
    val writer = use(CSVWriter.open(File(outfile)))
    val data = reader.iterator.drop(1) // drop header
    process(data)
      .foreach(x => writer.writeRow(x))
  }
}

@main def go(): Unit = {
  csvToTuple(List("42", "true", "Hello"))
//  readTransformWrite("data.csv", "output.csv", process) match {
//    case Success(xs) => println("Success")
//    case Failure(f)  => println(f)
//  }
}
