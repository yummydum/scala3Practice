package myutils
package encoder 

type Row = List[String]

trait RowEncoder[A]:
  def encodeRow(a: A): Row
