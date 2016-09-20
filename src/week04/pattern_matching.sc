// CONT. FROM DECOMPOSITION

// SPEC: an Expr class with subclasses Number, Sum, Product, and Var
// and methods eval, show, and simplify

// PREVIOUS ATTEMPTS with DOWNSIDES:
// classification and access methods: quadtratic explosion of methods
// type tests and casts: low-level and unsafe
// OO decomposition: does not always work, need to tocuh all classes to add new method
// , doesn't work for non-local

// OBSERVATION: the sole purpose of test and accessor functions is to REVERSE the construction process.
// - which subclass was used?
// - what were the arguments of the constructor?
// Solution is automated PATTERN MATCHING.

// CASE CLASSES
trait Expr
case class Number(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr

//what does the "case" do?
// 1: tells compiler to implicitly define companion objects with apply methods

object Number {
  def apply(n: Int) = new Number(n)
}

object Sum {
  def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}

// now you can just write num = Number(1) rather than num = NEW Number(1)

// PATTERN MATCHING
// can be seen as a generalization of "switch" statement from C/Java
// In Scala, it's with the keyword match, and can be applied to class heirarchies

def eval(e: Expr): Int = e match {
  case Number(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
}

// Patterns are constructed from:
// constructors, e.g. Number, Sum
// variables, e.g., n, e1, e2
// wildcard patterns "_"
// constants, e.g. 1, true

// for example: Sum(Number(1), Var(x)) would match objects of class sum with
// a left operand of type number and right operand of type var

// rules: variables start with lowercase, constants start with uppercase (exceptions null, true, false)
// same variable name can only appear once in a pattern

// so using eval( Sum( Number(1), Number(2) ) ) with the definition above:

eval(Sum(Number(1), Number(2)))
// becomes
Sum(Number(1), Number(2)) match {
  case Number(n) => n // Doesn't match
  case Sum(e1, e2) => eval(e1) + eval(e2) // matches
}
// becomes
eval(Number(1)) + eval(Number(2))

// Pattern Matching can also be implemented within the class heirarchy

trait ExprMatch {
  def eval: Int = this match {
    case NumberMatch(n) => n
    case SumMatch(e1, e2) => e1.eval + e2.eval
  }
}

case class NumberMatch(n: Int) extends ExprMatch
case class SumMatch(e1: ExprMatch, e2: ExprMatch) extends ExprMatch

def show(e: ExprMatch): String = e match {
  case NumberMatch(n) => n.toString
  case SumMatch(e1, e2) => show(e1) + " + " + show(e2)
}

show( SumMatch(NumberMatch(1), NumberMatch(3)) )
