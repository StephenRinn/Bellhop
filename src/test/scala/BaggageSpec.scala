import baggage.Baggage
import baggage.BaggageKey
import munit.CatsEffectSuite

class BaggageSpec extends CatsEffectSuite {
  test("baggage.Baggage Stores and retrieves typed values") {
    val userId = BaggageKey[String]("user_id")
    val requestId = BaggageKey[Long]("request_id")

    val baggage = Baggage()
      .set(userId, "user-123")
      .set(requestId, 456L)

    assertEquals(baggage.get(userId), Some("user-123"))
    assertEquals(baggage.get(requestId), Some(456L))
  }

  test("baggage.Baggage returns None for missing keys") {
    val userId = BaggageKey[String]("user_id")
    val baggage = Baggage()

    assertEquals(baggage.get(userId), None)
  }

  test("baggage.Baggage type-safety prevents incorrect type retrieval") {
    val userId = BaggageKey[String]("user_id")
    val baggage = Baggage().set[String](userId, "user-123")

    // This should return None because we're asking for Long, not String
    val wrongType = BaggageKey[Long]("user_id")
    println(wrongType)
    assertEquals(baggage.get(wrongType), None)
  }

  test("baggage.Baggage is immutable") {
    val userId = BaggageKey[String]("user_id")
    val baggage1 = Baggage().set(userId, "user-123")
    val baggage2 = baggage1.set(userId, "user-456")

    assertEquals(baggage1.get(userId), Some("user-123"))
    assertEquals(baggage2.get(userId), Some("user-456"))
  }
}
