package context

trait ContextStorage[F[_]] {
  def get: F[Context]
  def set(context: Context): F[Unit]
  def update(f: Context => Context): F[Unit]
}
