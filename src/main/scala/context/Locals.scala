package context

case class Locals (
    values: Map[LocalKey[_], Any] = Map.empty
                  )
