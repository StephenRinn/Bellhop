package baggage

import scala.reflect.ClassTag

case class BaggageKey[A](name: String)(implicit val ct: ClassTag[A])

