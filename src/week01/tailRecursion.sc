/// Euclid's algorithm for greatest common denominator:

def gcd(a: Int, b: Int): Int =
  if (b == 0) a else gcd(b, a % b)

// EXAMPLE - gcd(14, 21)
// if (21 == 0) 14 else gcd(21, 14 % 21)
// gcd(21, 14)
// if (14 == 0) 21 else gcd(14, 21 % 14)
// gcd(14, 7)
// if (7 == 0) 14 else gcd(7, 14 % 7)
// gcd(7, 0)
// if (0 == 0) 7 else gcd(0, 7 % 0)

def factorial(n: Int): Int =
  if (n == 0) 1 else n * factorial(n-1)

// if (4 == 0) 1 else 4 * factorial(4 - 1)
// 4 * factorial(3)
// 4 * (3 * factorial(2))
// 4 * (3 * (2 * factorial(1)))
// 4 * (3 * (2 * (1 * factorial(0))))
// 4 * (3 * (2 * (1 * 1))))

// TAIL RECURSION - if a function calls itself as its
// last action, the function's stack frame can be reused.
// This is called TAIL RECURSION.

// A tail recursive function is the functional version
// of a loop, and executes just as efficiently.

// GCD is tail recursive because the last evaluation is
// a call to GCD (after a % b is evaluated).

// Factorial is NOT tail recursive because n * (fact.result)
// is the last thing evaluated.

// You can see this in the reduction sequence - factorial
// must store values continuously throughout, while
// gcd doesnt.

// Tail recursion matters when the function could potentially
// go thousands of stack frames deep for some of its domain.
// Otherwise, CLARITY trumps EFFICIENCY.

// tail recursive factorial:
def factorialTail(n: Int): Int = {
  def loop(acc: Int, n: Int): Int =
    if (n == 0) acc
    else loop(acc * n, n-1)
  loop(1, n)
}

factorialTail(4)