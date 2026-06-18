package context

import org.typelevel.otel4s.AttributeKey

trait Span[F[_]] {
  def addEvent(name: String): F[Unit]
  def put[A](key: AttributeKey[A], value: A): F[Unit]
}
