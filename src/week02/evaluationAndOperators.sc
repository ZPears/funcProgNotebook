// Q: How is an instantiation of class C(e1, ..., em) evaluated?
// A: e1, ... em are evaluated like args of a normal function. That's it.

// The resulting expression, say new C(v1, ..., vm), is ALREADY A VALUE.

// Q: If we have an expression where a method is evaluated - say:
// class C(x1, ... xm){ ... def f(y1, ..., yn) = b ... }
// new C(v1, ..., vm).f(w1, ..., wn)

// A: new C(v1.... becomes:
// [ w1/y1, ... wn/yn ][v1/x1, ..., vm/xm][new C(v1, ..., vm)/this]b

// REMEMBER THE NOTATION [w1/y1, ..., wm/ym]B MEANS:
// 'the expression B in which all occurrences of yi have been replaced by wi

// SO YOU COULD THINK OF LINE 11 AS SAYING:
// f(w1, .. wn) evaluated in the light of some c(v1, ..., vn),
// which is new C(v1, ..., vn) / this, and then
// evaluating the body b in the light of all those

// OR, as they put it:
// the substitution of the formal parameters y1, ..., yn with args w1, ..., wn for f;
// sub. of formal params x1, ..., xm of class C with class args v1, ..., vm;
// sub of the self ref. "this" by the val of the object new C(v1, ..., vn)

class Rational(x: Int, y: Int) {
  // require - used to enforce a precondition on the caller of the function
  require(y != 0, "denominator must be nonzero")
  // assert - used to check the code of the function itself (NOT THE CALLERS FAULT)
  assert(x >= 0, "numerator must be non-negative")

  // MAKE YOUR OWN CONSTRUCTORS LIKE THIS:
  def this(x: Int) = {
    this(x, 1)
  }

  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  def numer = x / g
  def denom = y / g

  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)

  def neg: Rational =
    new Rational(
      -numer, denom
    )

//  def sub(that: Rational) =
//    add(that.neg)

  def - (that: Rational) =
    add(that.neg)

  def less(that: Rational) =
    numer * that.denom < that.numer * denom

  def max(that: Rational) =
    if (this.less(that)) that else this

  // if applying the simplification when printing:
  // override def toString = numer / g + "/" + denom / g
  override def toString = numer / g + "/" + denom / g
}

// EXAMPLES WITH RATIONAL CLASS

new Rational(1, 2).numer
// [1/x, 2/y][][new Rational(1,2)/this]x

new Rational(1, 2).less(new Rational(2, 3))
// [1/x, 2/y][new Rational(2, 3)/that][new Rational(1, 2)/this]Body
// new Rational(1, 2).numer * new Rational(2, 3).denom < new Rational(2, 3).numer * new Rational(1, 2).denom
// 1 * 3 < 2 * 2
// true

// MAKING METHOD CALLS CLEANER

// any method with a param in scala can be used like an infix operator - examples:
// r add s == r.add(s)
// r less s == r less s
// r max s == r max s

// OPERATORS CAN BE USED AS IDENTIFIERS
// normal alphanumeric identifiers exist, but also there are
// SYMBOLIC identifiers, which start with operator symbol
// '_' counts as a letter
// * , +?%&, vector_++ are all valid identifiers

// SEE THE TWO DIFFERENT VERSIONS OF sub IN RATIONAL CLASS FOR EXAMPLE

// to define a negative, use def unary_- : Class =

// OPERATOR PRECEDENCE IN SCALA

// determined by the operator's first character:

// THE ORDER IS (from lowest priority to highest):
// (all letters)
// |
// ^
// &
// < >
// = !
// :
// + -
// * / %
// (all other special characters)

// EXERCISE - provide fully parenthized version of
// a + b ^? c ?^ d less a ==> b | c

// ((a + b) ^? (c ?^ d)) less ((a ==> b) | c)