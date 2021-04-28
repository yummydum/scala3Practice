package myutils.func
import myutils.encoder.RowEncoder

def tupleToCsv[X <: Tuple: RowEncoder](tuple: X): List[String] =
  summon[RowEncoder[X]].encodeRow(tuple)
