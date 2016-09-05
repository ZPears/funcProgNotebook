object Session {
  println("Welcome to the Scala Worksheet")
  1 + 2
  def abs(x: Double): Double = if (x < 0) -x else x

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double): Boolean =
    abs(guess * guess - x) < x * 0.00001

  def improve(guess: Double, x: Double): Double =
    (guess + x / guess)  / 2

  def sqrt(x: Double): Double = sqrtIter(1.0, x)

  sqrt(2)
  sqrt(3e27)
  sqrt(0.001)
  sqrt(0.1e-20)
  sqrt(1.0e20)
  sqrt(1.0e50)

}

///CLEANER WAY TO IMPLEMENT:
/// DEFINE THE FUNCTIONS INSIDE THE OTHER FUNCTION
/// AVOIDS NAME SPACE POLLUTION

/// USE BLOCKS DELIMITED BY BRACES:

def sqrt(x: Double) = {

  def sqrtIter(guess: Double, x: Double): Double =
    if (isGoodEnough(guess, x)) guess
    else sqrtIter(improve(guess, x), x)

  def isGoodEnough(guess: Double, x: Double): Boolean =
    abs(guess * guess - x) < x * 0.00001

  def improve(guess: Double, x: Double): Double =
    (guess + x / guess) / 2

  sqrtIter(1.0, x)

}

/// DEFINITIONS INSIDE THE BLOCK ARE ONLY VISIBLE WITHIN
/// THE DEFINITIONS INSIDE A BLOCK SHADOW THE SAME NAMES
/// OUTSIDE THE BLOCK.

/// THIS MEANS YOU CAN ELIMINATE THE X'S IN THE ABOVE SQRT:

def sqrt(x: Double) = {

  def sqrtIter(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else sqrtIter(improve(guess))

  def isGoodEnough(guess: Double): Boolean =
    abs(guess * guess - x) < x * 0.00001

  def improve(guess: Double): Double =
    (guess + x / guess) / 2

  sqrtIter(1.0)

}

