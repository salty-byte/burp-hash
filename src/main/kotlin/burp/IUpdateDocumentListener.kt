package burp

import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

fun interface IUpdateDocumentListener : DocumentListener {
  fun update(e: DocumentEvent)

  override fun insertUpdate(e: DocumentEvent) {
    update(e)
  }

  override fun removeUpdate(e: DocumentEvent) {
    update(e)
  }

  override fun changedUpdate(e: DocumentEvent) {
    update(e)
  }
}
