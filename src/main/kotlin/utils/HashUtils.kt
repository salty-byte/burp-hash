package utils

import java.security.MessageDigest

class HashUtils {

  init {
    throw IllegalStateException("Utility class")
  }

  companion object {
    fun calcSha256(text: String): String {
      return MessageDigest.getInstance("SHA-256")
        .digest(text.toByteArray())
        .joinToString(separator = "") {
          "%02x".format(it)
        }
    }
  }
}
