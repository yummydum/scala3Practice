import myutils.{productToCsv, csvToProduct, Row}
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

def process(data: Iterator[Row]): Iterator[Row] = {
  data
    .map(x => csvToProduct[Airline](x))
    .filter(x => x.avail_seat_km_per_week <= 400000000)
    .map(x => productToCsv(x))
}

def readTransformWrite[A](
    infile: String,
    outfile: String,
    func: Iterator[Row] => Iterator[Row]
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
  readTransformWrite("data.csv", "output.csv", process) match {
    case Success(xs) => println("Success")
    case Failure(f)  => println(f)
  }
}
