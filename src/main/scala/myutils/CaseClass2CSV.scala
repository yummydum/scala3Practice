package myutils
package encoder
package instances
package func
 
def tupleToCsv[X <: Tuple: RowEncoder](tuple: X): List[String] =
  summon[RowEncoder[X]].encodeRow(tuple)
