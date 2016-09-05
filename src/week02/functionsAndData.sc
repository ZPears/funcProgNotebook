// On how functions can create and encapsulate data structures.

// EXAMPLE: Rational numbers package

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

  def sub(that: Rational) =
    add(that.neg)

  def less(that: Rational) =
    numer * that.denom < that.numer * denom

  def max(that: Rational) =
    if (this.less(that)) that else this

  // if applying the simplification when printing:
  // override def toString = numer / g + "/" + denom / g
  override def toString = numer / g + "/" + denom / g
}

// Class gives new type (Rational) and new constructor for Rational
// Scala keeps types and values in different namespaces,
// so no possibility of conflict there.

val x = new Rational(1, 3)
x.numer
x.denom

val y = new Rational(5, 7)
x.add(y)

val z = new Rational(3, 2)

x.sub(y).sub(z)

x.neg

// MORE WORK WITH RATIONALS - NEXT LECTURE

// Rational objects need to be simplified before they're printed

// this is done by implementing the private val g and using it
// in the definitions of numer and denom

// OPTIONS FOR NUMER AND DENOM -
// def numer = x / gcd(x, y) - advantageous if numer and denom are called infrequently
// val numer = x / gcd(x, y) - advantageous if they are called often (since val only computes once)
// the key is that THE CLIENT DOESN'T KNOW THE DIFFERENCE EITHER WAY
// this is DATA ABSTRACTION