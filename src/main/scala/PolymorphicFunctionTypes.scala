import PolymorphicFunctionTypes.Expr.{Apply, Var}

/**
 * Polymorphic Function Types: https://dotty.epfl.ch/docs/reference/new-types/polymorphic-function-types.html
 */
object PolymorphicFunctionTypes:
  enum Expr[A]:
    case Var(name: String)
    case Apply[A, B](fun: Expr[B => A], arg: Expr[B]) extends Expr[A]

  def mapSubexpressions[A](e: Expr[A])(f: [B] => Expr[B] => Expr[B]): Expr[A] =
    e match
      case Apply(fun, arg) => Apply(f(fun), f(arg))
      case Var(n) => Var(n)

  def test(): Unit =
    val e0 = Apply(Var("f"), Var("a"))
    val e1 = mapSubexpressions(e0)([B] => (se: Expr[B]) => Apply(Var[B => B]("wrap"), se))
    println(e1)
