import baggage.Baggage
import baggage.BaggageKey
import context.Context
import context.SpanContext
import io.opentelemetry.sdk.trace.IdGenerator
import munit.CatsEffectSuite

class ContextSpec extends CatsEffectSuite {
  val UserId: BaggageKey[String] = BaggageKey[String]("user.id")

  val generator: IdGenerator = IdGenerator.random()
  def newTraceIdHex: String = generator.generateTraceId()

  test("empty context contains empty baggage") {
    val context = Context()

    assertEquals(
      context.baggage,
      Baggage(),
    )
  }

  test("empty context contains no span") {
    val context = Context()

    assertEquals(
      context.span,
      None,
    )
  }

  test("withBaggage replaces baggage") {
    val baggage =
      Baggage()
        .set(UserId, "abc")

    val context =
      Context()
        .withBaggage(baggage)

    assertEquals(context.baggage.get(UserId), Some("abc"))
  }

  test("withSpan updates span without affecting baggage") {

    val baggage =
      Baggage()
        .set(UserId, "abc")

    val span =
      SpanContext(
        newTraceIdHex,
        newTraceIdHex,
        None,
        sampled = true,
      )

    val context =
      Context()
        .withBaggage(baggage)
        .withSpan(span)

    assertEquals(
      context.baggage.get(UserId),
      Some("abc"),
    )

    assertEquals(
      context.span,
      Some(span),
    )
  }
}
