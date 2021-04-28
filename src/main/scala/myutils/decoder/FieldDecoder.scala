package myutils.decoder

trait FieldDecoder[A]:
  def decodeField(a: String): A
