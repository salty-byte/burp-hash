package utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import kotlin.test.assertFails

class JsonUtilsTest {

  @Test
  fun values() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        },
        "param_b": [
            {
                "id": "111",
                "title": "apple"
            },
            {
                "id": "112",
                "title": "test"
            }
        ]
    }
    """.trimIndent()
    val values = JsonUtils.values(Json.parseToJsonElement(json))
    assertEquals(6, values.size)
    assertEquals("1", values[0])
    assertEquals("111", values[2])
  }

  @Test
  fun valuesToString() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        },
        "param_b": [
            {
                "id": "111",
                "title": "apple"
            },
            {
                "id": "112",
                "title": "test"
            }
        ]
    }
    """.trimIndent()
    assertEquals(
      "1test111apple112test",
      JsonUtils.valuesString(json)
    )
  }

  @Test
  fun valuesToStringFailsIfJsonIsNotValid() {
    assertFails {
      val json = """
      {
          "param_a": {
              "id": 1,
              "name": "test",
          }
      }
      """.trimIndent()
      JsonUtils.valuesString(json)
    }
  }

  @Test
  fun addParam() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        },
        "param_b": [
            {
                "id": "111",
                "title": "apple"
            },
            {
                "id": "112",
                "title": "test"
            }
        ]
    }
    """.trimIndent()
    val addedJson = JsonUtils.addParam(json, "hash", "1234567abcdef")
    assertEquals(
      "1234567abcdef",
      Json.parseToJsonElement(addedJson).jsonObject["hash"]!!.jsonPrimitive.content
    )
  }

  @Test
  fun addParamExist() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        },
        "hash": "test"
    }
    """.trimIndent()
    val addedJson = JsonUtils.addParam(json, "hash", "1234567abcdef")
    assertEquals(
      "1234567abcdef",
      Json.parseToJsonElement(addedJson).jsonObject["hash"]!!.jsonPrimitive.content
    )
  }

  @Test
  fun removeParam() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        },
        "param_b": [
            {
                "id": "111",
                "title": "apple"
            },
            {
                "id": "112",
                "title": "test"
            }
        ],
        "hash": "1234567abcdef"
    }
    """.trimIndent()
    val removedJson = JsonUtils.removeParam(json, "hash")
    assertFalse(
      Json.parseToJsonElement(removedJson).jsonObject.containsKey("hash")
    )
  }

  @Test
  fun removeParamNotExist() {
    val json = """
    {
        "param_a": {
            "id": 1,
            "name": "test"
        }
    }
    """.trimIndent()
    val removedJson = JsonUtils.removeParam(json, "hash")
    assertFalse(
      Json.parseToJsonElement(removedJson).jsonObject.containsKey("hash")
    )
  }
}
