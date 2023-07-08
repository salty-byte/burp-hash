package burp

class BurpExtender : IBurpExtender {

  override fun registerExtenderCallbacks(callbacks: IBurpExtenderCallbacks) {
    val handler = HashCalcHandler(callbacks)
    val tab = HashCalcTab(handler)
    callbacks.registerSessionHandlingAction(handler)
    callbacks.addSuiteTab(tab)
  }
}
