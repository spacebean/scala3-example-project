/**
 * Universal Apply Methods: https://dotty.epfl.ch/docs/reference/other-new-features/creator-applications.html
 */
object UniversalApplyMethods:
  class StringBuilder(s: String):
    def this() =
      this("")

  def test(): Unit =
    println(UniversalApplyMethods.StringBuilder("abc"))
    println(UniversalApplyMethods.StringBuilder())
