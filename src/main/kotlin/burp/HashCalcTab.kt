package burp

import java.awt.Component
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class HashCalcTab(callbacks: IHashCalcAction) : ITab {
  private val component: JComponent

  init {
    component = JPanel()
    val label = JLabel("Target Parameter Name")
    val textField = JTextField("hash", 100)
    textField.document.addDocumentListener(
      IUpdateDocumentListener { _ ->
        callbacks.updateKey(textField.text)
      }
    )
    component.add(label)
    component.add(textField)
  }

  override fun getTabCaption(): String {
    return "Calc Hash"
  }

  override fun getUiComponent(): Component {
    return component
  }
}
