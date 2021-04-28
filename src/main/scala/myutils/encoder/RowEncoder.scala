package myutils
package encoder


trait RowEncoder[A]:
  def encodeRow(a: A): Row
