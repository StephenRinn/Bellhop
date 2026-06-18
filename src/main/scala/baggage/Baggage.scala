package baggage

case class Baggage(values: Map[String, Any] = Map.empty) {
  def get[A](key: BaggageKey[A]): Option[A] = {
    values.get(key.name).flatMap {
      case key.ct(value) => Some(value)
      case _ => None
    }
  }

  def set[A](key: BaggageKey[A], value: A): Baggage = {
    copy(values = values.updated(key.name, value))
  }
}
