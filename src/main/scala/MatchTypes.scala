/**
 * Match Types: https://dotty.epfl.ch/docs/reference/new-types/match-types.html
 */
object MatchTypes:
  type Elem[X] =
    X match
      case String => Char
      case Array[t] => t
      case Iterable[t] => t

  type LeafElem[X] =
    X match
      case String => Char
      case Array[t] => LeafElem[t]
      case Iterable[t] => LeafElem[t]
      case AnyVal => X

  // Dependent Typing
  @annotation.tailrec
  def leafElem[X](x: X): LeafElem[X] =
    x match
      case x: String => x.charAt(0)
      case x: Array[t] => leafElem(x(9))
      case x: Iterable[t] => leafElem(x.head)
      case x: AnyVal => x

  type Concat[Xs <: Tuple, +Ys <: Tuple] <: Tuple =
    Xs match
      case EmptyTuple => Ys
      case x *: xs => x *: Concat[xs, Ys]

  def test(): Unit =
    val c: LeafElem[String] = leafElem("qweasdzxcrfv")
    println(c)

    val t: Concat[EmptyTuple, Tuple3[Int, Int, Int]] = (3, 4, 5)
    val t2: Concat[Tuple2[Int, Int], Tuple3[Int, Int, Int]] = (1, 2, 3, 4, 5)
    println(t)
    println(t2)
