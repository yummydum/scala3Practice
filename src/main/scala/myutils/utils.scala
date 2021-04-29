package myutils
import myutils.encoder.RowEncoder
import myutils.decoder.RowDecoder
import scala.deriving.Mirror

type Row = List[String]

def tupleToCsv[X <: Tuple](tuple: X)(using e: RowEncoder[X]): List[String] =
  e.encodeRow(tuple)

def productToCsv[P <: Product](
    prod: P
)(using p: Mirror.ProductOf[P], e: RowEncoder[p.MirroredElemTypes]) =
  e.encodeRow(Tuple.fromProductTyped(prod))

def csvToTuple[X <: Tuple](row: Row)(using d: RowDecoder[X]): X =
  d.decodeRow(row)

def csvToProduct[P](
    row: Row
)(using p: Mirror.ProductOf[P], d: RowDecoder[p.MirroredElemTypes]): P =
  p.fromProduct(d.decodeRow(row))
