package utils

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HashUtilsTest {

  @Test
  fun calcSha256() {
    assertEquals(
      "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08",
      HashUtils.calcSha256("test")
    )
    assertEquals(
      "120dddbc177eedcf2727b0b38deb2082b061ea3e7ca7769ace94f331a58b25b3",
      HashUtils.calcSha256("!\"#$%&ABc'()'")
    )
    assertEquals(
      "7a7fe04d5ba4adee2f39d6462224ab879542c79d51dc85f45c1ef9ec18ac1bd2",
      HashUtils.calcSha256("1test111apple112testABCD")
    )
  }
}
