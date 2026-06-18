package context

import baggage.Baggage

final case class Context(
    span: Option[SpanContext] = None,
    baggage: Baggage = Baggage(),
    locals: Locals = Locals(),
) {
  def withSpan(span: SpanContext): Context =
    copy(span = Some(span))

  def withoutSpan: Context =
    copy(span = None)

  def withBaggage(baggage: Baggage): Context =
    copy(baggage = baggage)

  def withLocals(locals: Locals): Context =
    copy(locals = locals)
}
