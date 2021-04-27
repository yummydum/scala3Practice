package myutils
package encoder
package instances

given FieldEncoder[Int] with
  def encodeField(x: Int) = x.toString

given FieldEncoder[Boolean] with
  def encodeField(x: Boolean) = if x then "true" else "false"

given FieldEncoder[String] with
  def encodeField(x: String) = x
