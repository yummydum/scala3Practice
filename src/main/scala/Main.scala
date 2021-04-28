import java.io.{BufferedWriter, FileWriter}
import scala.io.Source.fromFile
import scala.util.{Failure, Success, Using}

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

def parse(line: String) = {
  val row = line.split(',')
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

def process(data: Iterator[String]): Iterator[String] = {
  data
    .map(parse)
    .filter(x => x.avail_seat_km_per_week <= 400000000)
    .map(x => s"${x.airline},${x.fatalities_85_99}\n")
}

def readTransformWrite[A](
    infile: String,
    outfile: String,
    func: Iterator[String] => Iterator[String],
    header: Boolean = true
) = {
  Using.Manager { use =>
    val reader = use(fromFile(infile))
    val writer = use(new BufferedWriter(new FileWriter(outfile)))
    val data = reader.getLines().drop(1) // Drop header
    for (line <- func(data)) {
      writer.write(line)
    }
  }
}

@main def go(): Unit = {
  readTransformWrite("data.csv", "output.csv", process) match {
    case Success(xs) => println("Success")
    case Failure(_)  => println("Failure")
  }
}
