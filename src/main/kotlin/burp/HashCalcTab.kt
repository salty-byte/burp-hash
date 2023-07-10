package burp

import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.awt.Insets
import javax.swing.*

class HashCalcTab(callbacks: IHashCalcAction) : ITab {
  private val component: JComponent

  init {
    component = JPanel()
    val targetLabel = JLabel("Target Parameter Name: ")
    targetLabel.horizontalAlignment = JLabel.RIGHT
    val targetTextField = JTextField("hash", 100)
    targetTextField.document.addDocumentListener(
      IUpdateDocumentListener { _ ->
        callbacks.updateKey(targetTextField.text)
      }
    )
    val saltLabel = JLabel("Hash salt: ")
    saltLabel.horizontalAlignment = JLabel.RIGHT
    val saltTextField = JTextField("", 100)
    saltTextField.document.addDocumentListener(
      IUpdateDocumentListener { _ ->
        callbacks.updateSalt(saltTextField.text)
      }
    )

    component.setLayout(GridBagLayout())
    val gbc = GridBagConstraints()
    gbc.fill = GridBagConstraints.HORIZONTAL
    gbc.gridx = 0
    gbc.gridy = 0
    gbc.weightx = 0.0
    gbc.weighty = 0.0
    gbc.insets = Insets(20, 20, 0, 5)
    component.add(targetLabel, gbc)
    gbc.gridx++
    gbc.weightx = 1.0
    gbc.insets = Insets(20, 5, 0, 20)
    component.add(targetTextField, gbc)
    gbc.gridx = 0
    gbc.gridy = 1
    gbc.weightx = 0.0
    gbc.insets = Insets(10, 20, 0, 5)
    component.add(saltLabel, gbc)
    gbc.gridx++
    gbc.weightx = 1.0
    gbc.insets = Insets(10, 5, 0, 20)
    component.add(saltTextField, gbc)
    gbc.gridy++
    gbc.weighty = 1.0
    component.add(JPanel(), gbc)
  }

  override fun getTabCaption(): String {
    return "Calc Hash"
  }

  override fun getUiComponent(): Component {
    return component
  }
}
