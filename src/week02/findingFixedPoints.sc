// A FUNCTION FIXED POINT is any point such that:
// f(x) = x

// for some functions, we can find fixed points by starting with an estimate
// and then applying f repetitively:
// x, f(x), f(f(x)), f(f(f(x))), etc., etc.
// until the return value does not vary anymore (or change is sufficiently small)

// example of fixed point algorithm:

def abs(x: Double): Double = {
  if (x >= 0) x else -x
}

val tolerance = 0.0001
def isCloseEnough(x: Double, y: Double) =
  abs((x - y) / x) / x < tolerance

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def iterate(guess: Double): Double = {
    val next = f(guess)
    if (isCloseEnough(guess, next)) next
    else iterate(next)
  }
  iterate(firstGuess)
}

fixedPoint(x => 1 + x/2)(1)

// can we apply to sqrts?
// a sqrt is a number y such that y * y = x.
// therefore, a sqrt is a number y such that y = x / y
// so sqrt(x) is a fixed point of the function (y => x / y)

//def sqrt(x: Double) = fixedPoint(y => (y + x / y) / 2)(1)
//sqrt(4)

def averageDamp(f: Double => Double)(x: Double) = (x + f(x)) / 2

def sqrt(x: Double) = fixedPoint(averageDamp(y => x/y))(1)

sqrt(4)