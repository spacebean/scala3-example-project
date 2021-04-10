/**
 * Opaque Type Aliases: https://dotty.epfl.ch/docs/reference/other-new-features/opaques.html
 */
object OpaqueTypeAliases:
  object MyMath:

    opaque type Logarithm = Double

    object Logarithm:
      def apply(d: Double): Logarithm =
        math.log(d)

      def safe(d: Double): Option[Logarithm] =
        if d > 0.0 then Some(math.log(d))
        else None
    end Logarithm

    // Extension methods define opaque types' public APIs
    extension (x: Logarithm)
      def toDouble: Double =
        math.exp(x)

      def +(y: Logarithm): Logarithm =
        Logarithm(math.exp(x) + math.exp(y))

      def *(y: Logarithm): Logarithm =
        x + y

  end MyMath

  object Access:

    opaque type Permissions = Int
    opaque type PermissionChoice = Int
    opaque type Permission <: Permissions & PermissionChoice = Int

    extension (x: Permissions)
      def &(y: Permissions): Permissions =
        x | y

    extension (x: PermissionChoice)
      def |(y: PermissionChoice): PermissionChoice =
        x | y

    extension (granted: Permissions)
      def is(required: Permissions) =
        (granted & required) == required

    extension (granted: Permissions)
      def isOneOf(required: PermissionChoice) =
        (granted & required) != 0

    val NoPermission: Permission = 0
    val Read: Permission = 1
    val Write: Permission = 2
    val ReadWrite: Permissions = Read | Write
    val ReadOrWrite: PermissionChoice = Read | Write

  end Access

  object User:
    import Access.*

    case class Item(rights: Permissions)

    val roItem = Item(Read)
    val rwItem = Item(ReadWrite)
    val noItem = Item(NoPermission)

    assert(!roItem.rights.is(ReadWrite))
    assert(roItem.rights.isOneOf(ReadOrWrite))

    assert(rwItem.rights.is(ReadWrite))
    assert(rwItem.rights.isOneOf(ReadOrWrite))

    assert(!noItem.rights.is(ReadWrite))
    assert(!noItem.rights.isOneOf(ReadOrWrite))
  end User

  def test(): Unit =
    import MyMath.Logarithm

    val l = Logarithm(1.0)
    val l2 = Logarithm(2.0)
    val l3 = l * l2
    val l4 = l + l2

    // val d: Double = l // error: found: Logarithm, required: Double
    // val l5: Logarithm = 1.0 // error: found: Double, required: Logarithm
    // println(l * 2) // error: found: Int(2), required: Logarithm
    // println(l / l5) // error: `/` is not a member of Logarithm
