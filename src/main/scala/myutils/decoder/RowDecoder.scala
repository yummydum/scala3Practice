package myutils
package decoder

trait RowDecoder[A <: Tuple]:
  def decodeRow(a: Row): A
