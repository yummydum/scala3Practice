package myutils
import myutils.encoder.RowEncoder
import myutils.decoder.RowDecoder

type Row = List[String]

def tupleToCsv[X <: Tuple: RowEncoder](tuple: X): List[String] =
  summon[RowEncoder[X]].encodeRow(tuple)

def csvToTuple[X <: Tuple: RowDecoder](row: Row): X =
  summon[RowDecoder[X]].decodeRow(row)
