import myutils.encoder.instances.func.tupleToCsv
import myutils.encoder.instances.given

@main def hello: Unit = {
  println(tupleToCsv((42, true, "Hello")))
}
