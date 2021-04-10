@main
def Main(args: String*): Unit =
  runExample("Trait Params")(TraitParams.test())

  runExample("Enum Types")(EnumTypes.test())

  runExample("Context Functions")(ContextFunctions.test())

  runExample("Given Instances")(GivenInstances.test())

  runExample("Conversion")(Conversion.test())

  runExample("Union Types")(UnionTypes.test())

  runExample("Intersection Types")(IntersectionTypes.test())

  runExample("Type Lambda")(TypeLambdas.test())

  runExample("Multiversal Equality")(MultiversalEquality.test())

  runExample("Parameter Untupling")(ParameterUntupling.test())

  runExample("Structural Types")(StructuralTypes.test())

  runExample("Pattern Matching")(PatternMatching.test())

  runExample("Export Clauses")(ExportClauses.test())

  runExample("Extension Methods")(ExtensionMethods.test())

  runExample("Opaque Type Aliases")(OpaqueTypeAliases.test())

  runExample("Polymorphic Function Types")(PolymorphicFunctionTypes.test())

  runExample("Transparent Traits")(TransparentTraits.test())

  runExample("Universal Apply Methods")(UniversalApplyMethods.test())

  runExample("Dependent Function Types")(DependentFunctionTypes.test())

  runExample("Match Types")(MatchTypes.test())
end Main

private def runExample(name: String)(f: => Unit): Unit =
  println(Console.MAGENTA + s"$name example:" + Console.RESET)
  f
  println()
