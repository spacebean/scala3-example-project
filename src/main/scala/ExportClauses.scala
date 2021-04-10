/**
 * Export Clauses: https://dotty.epfl.ch/docs/reference/other-new-features/export.html
 */
object ExportClauses:
  class BitMap

  class InkJet

  class Printer:
    type PrinterType

    def print(bits: BitMap): Unit =
      println(bits)

    def status: List[String] =
      List("ready")

  class Scanner:
    def scan(): BitMap =
      new BitMap

    def status: List[String] =
      List("ready")

  class Copier:
    private val printUnit =
      new Printer:
        type PrinterType = InkJet
    private val scanUnit = new Scanner

    export scanUnit.scan
    export printUnit.{status => _, _}

    def status: List[String] =
      printUnit.status ++ scanUnit.status

  def test(): Unit =
    val copier = new Copier
    copier.print(copier.scan())
