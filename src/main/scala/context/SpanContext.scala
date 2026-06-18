package context

case class SpanContext(
    traceId: String,
    spanId: String,
    parent: Option[String],
    sampled: Boolean,
)
