package context

import scala.reflect.ClassTag

case class LocalKey[A](name: String)(implicit val ct: ClassTag[A])
