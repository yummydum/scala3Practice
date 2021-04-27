package myutils
package encoder 
package instances

given RowEncoder[EmptyTuple] with
  def encodeRow(empty: EmptyTuple) =
    List.empty

given [H: FieldEncoder, T <: Tuple: RowEncoder]: RowEncoder[H *: T] with
  def encodeRow(tuple: H *: T) =
    summon[FieldEncoder[H]].encodeField(tuple.head) :: summon[RowEncoder[T]]
      .encodeRow(tuple.tail)
