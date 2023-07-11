package burp

interface IHashCalcAction {
  fun updateKey(key: String)
  fun updateSalt(salt: String)
  fun updateSkips(skips: List<String>)
}
