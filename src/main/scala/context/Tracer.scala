package context

trait Tracer[F[_]] {
  def span[A](name: String)(fa: F[A]): F[A]
}
