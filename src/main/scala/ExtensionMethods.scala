/**
 * Extension Methods: https://dotty.epfl.ch/docs/reference/contextual/extension-methods.html
 */
object ExtensionMethods:
  object circle:

    case class Circle(x: Double, y: Double, radius: Double)

    extension (c: Circle)
      def circumference: Double =
        c.radius * math.Pi * 2

  object operators:

    extension (x: String)
      def <(y: String): Boolean =
        x.length < y.length

    extension (x: Int)
      def +:(xs: Seq[Int]): Seq[Int] =
        x +: xs

    extension (x: Int)
      infix def min(y: Int): Int =
        Math.min(x, y)

  object genericExtensions:

    extension[T] (xs: List[T])
      def second =
        xs.tail.head

    extension[T] (x: T)(using Numeric[T])
      def +(y: T): T =
        summon[Numeric[T]].plus(x, y)

  object collectiveExtensions:

    import math.Ordered.orderingToOrdered

    extension (ss: Seq[String])
      def longestStrings: Seq[String] =
        val maxLength = ss.map(_.length).max
        ss.filter(_.length == maxLength)

      def longestString: String =
        longestStrings.head

    extension[T] (xs: List[T])(using Ordering[T])
      def smallest(n: Int): List[T] =
        xs.sorted.take(n)

      def smallestIndices(n: Int): List[Int] =
        val limit = smallest(n).max
        xs.zipWithIndex.collect { case (x: T, i) if x <= limit => i }

  def test(): Unit =
    val c = circle.Circle(0, 0, 1)
    println(c.circumference)
    println("ab" < "c")
    println(1 +: List(2, 3))
    println(2 min 3)
