package myutils
package decoder
package instances

given FieldDecoder[Int] with
  def decodeField(x: String) = x.toInt

given FieldDecoder[Boolean] with
  def decodeField(x: String) = x.toBoolean

given FieldDecoder[Long] with
  def decodeField(x: String) = x.toLong

given FieldDecoder[String] with
  def decodeField(x: String) = x
