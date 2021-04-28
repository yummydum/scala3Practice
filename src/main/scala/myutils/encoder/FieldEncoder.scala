package myutils
package encoder

trait FieldEncoder[A]:
  def encodeField(a: A): String
