package utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*

class JsonUtils {

  init {
    throw IllegalStateException("Utility class")
  }

  companion object {
    fun addParam(json: String, key: String, value: String): String {
      val element = Json.parseToJsonElement(json).jsonObject
      val map = element.plus(Pair(key, JsonPrimitive(value)))
      return Json.encodeToString(map)
    }

    fun removeParam(json: String, key: String): String {
      val element = Json.parseToJsonElement(json).jsonObject
      if (!element.containsKey(key)) {
        return json
      }
      return Json.encodeToString(element.minus(key))
    }

    fun values(element: JsonElement): List<String> {
      val list = mutableListOf<String>()
      element.jsonObject.values
        .forEach { value ->
          try {
            value.jsonArray.forEach {
              list.addAll(values(it))
            }
          } catch (_: IllegalArgumentException) {
            // do nothing
          }

          try {
            list.addAll(values(value.jsonObject))
          } catch (_: IllegalArgumentException) {
            // do nothing
          }

          try {
            list.add(value.jsonPrimitive.content)
          } catch (_: IllegalArgumentException) {
            // do nothing
          }
        }
      return list
    }

    fun valuesString(json: String): String {
      val values = values(Json.parseToJsonElement(json))
      return values.joinToString(separator = "")
    }
  }
}
