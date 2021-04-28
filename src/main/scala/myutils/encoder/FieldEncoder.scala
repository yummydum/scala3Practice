package myutils.encoder

trait FieldEncoder[A]:
  def encodeField(a: A): String
