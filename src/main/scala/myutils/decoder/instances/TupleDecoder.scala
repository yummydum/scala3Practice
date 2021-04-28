package myutils
package decoder
package instances

given RowDecoder[EmptyTuple] with
  def decodeRow(row: Row) = EmptyTuple

// Type parameter H: FieldDecoder, T <: Tuple: RowDecoder
// Given instalce for RowDecoder[H *: T]
given [H: FieldDecoder, T <: Tuple: RowDecoder]: RowDecoder[H *: T] with
  def decodeRow(row: Row) =
    summon[FieldDecoder[H]].decodeField(row.head) *: summon[RowDecoder[T]]
      .decodeRow(row.tail)
