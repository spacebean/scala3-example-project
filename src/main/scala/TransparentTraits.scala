/**
 * Transparent Traits: https://dotty.epfl.ch/docs/reference/other-new-features/transparent-traits.html
 */
object TransparentTraits:
  transparent trait S

  trait Kind

  object Var extends Kind, S

  object Val extends Kind, S

  def test(): Unit =
    val x = Set(if true then Val else Var)
    println(x)
