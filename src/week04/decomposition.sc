import java.beans.Expression

import sun.font.TrueTypeFont

// DECOMPOSITION

// writing a small interpreter for arithmetic expressions
// that only has numbers and additions.
// Will be accomplished with base trait Expr and two subclasses, Number and Sum.
// Will take form of tree: Expr -> Number, Expr -> Sum


trait Expr {
  def isNumber: Boolean
  def isSum: Boolean
  def numValue: Int
  def leftOp: Expr
  def rightOp: Expr
}

class Number(n: Int) extends Expr {
  def isNumber: Boolean = true
  def isSum: Boolean = false
  def numValue: Int = n
  def leftOp: Expr = throw new Error("Number.leftOp")
  def rightOp: Expr = throw new Error("Number.rightOp")
}

class Sum(e1: Expr, e2: Expr) extends Expr {
  def isNumber: Boolean = false
  def isSum: Boolean = true
  def numValue: Int = throw new Error("Sum.numValue")
  def leftOp: Expr = e1
  def rightOp: Expr = e2
}

def eval(e: Expr): Int = {
  if (e.isNumber) e.numValue
  else if (e.isSum) eval(e.leftOp) + eval(e.rightOp)
  else throw new Error("Unknown Expression: " + e)
}

// how to stop implementing trait methods for all classes that extend them, when they're redundant?

// you can type cast and type test with:
// x.isInstanceOf[T]
// x.asInstanceOf[T]
// but it's discouraged, because scala has better solutions

// here's a type testing version of eval:

def evalTypeTest(e: Expr): Int = {
  if (e.isInstanceOf[Number])
    e.asInstanceOf[Number].numValue
  else if (e.isInstanceOf[Sum])
    evalTypeTest(e.asInstanceOf[Sum].leftOp) +
    evalTypeTest(e.asInstanceOf[Sum].rightOp)
  else throw new Error("Unknown Expression: " + e)
}

// BENEFITS: No need for classification methods
// DRAWBACKS: Low-level and potentially unsafe, because you don't know at runtime that typecast
// will succeed (might throw a classCastException

// OO decomposition of it

trait ooExpr {
  def eval: Int
}

class ooNumber(n: Int) extends ooExpr {
  def eval: Int = n
}

class ooSum(e1: ooExpr, e2: ooExpr) extends ooExpr {
  def eval: Int = e1.eval + e2.eval
}

// Problem with OO solution - how to handle expr that distribute? like:
// a * b + a * c == a * (b + c)
// solution is non-local, so cannot be encapsulated in the method of a single object
// now back at square one - need test and access methods for all different subclasses


