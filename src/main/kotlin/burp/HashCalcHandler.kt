package burp

import burp.IRequestInfo.CONTENT_TYPE_JSON
import utils.HashUtils
import utils.JsonUtils

class HashCalcHandler(private val callbacks: IBurpExtenderCallbacks) : ISessionHandlingAction,
  IHashCalcAction {
  private var key: String = "hash"

  override fun updateKey(key: String) {
    this.key = key
  }

  override fun getActionName(): String {
    return "Calc Hash"
  }

  override fun performAction(
    currentRequest: IHttpRequestResponse,
    macros: Array<out IHttpRequestResponse>?
  ) {
    try {
      val helpers = callbacks.helpers
      val requestInfo = helpers.analyzeRequest(currentRequest)
      if (requestInfo.contentType != CONTENT_TYPE_JSON) {
        return
      }

      val bodyOffset = requestInfo.bodyOffset
      val requestBytes = currentRequest.request
      val json = requestBytes.sliceArray(bodyOffset until requestBytes.size).decodeToString()
      val targetJson = JsonUtils.removeParam(json, key)
      val valuesStr = JsonUtils.valuesString(targetJson)
      val hash = HashUtils.calcSha256(valuesStr)
      val updatedJson = JsonUtils.addParam(json, key, hash)
      currentRequest.request = helpers.buildHttpMessage(
        requestInfo.headers,
        updatedJson.toByteArray()
      )
    } catch (e: Exception) {
      callbacks.printError("exception: $e")
    }
  }
}
