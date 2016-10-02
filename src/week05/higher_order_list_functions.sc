// applying functions to elements of a list

def scaleList(xs: List[Double], factor: Double): List[Double] = xs match {
  case Nil => xs
  case y :: ys => y * factor :: scaleList(ys, factor)
}

// can be generalized to...

abstract class List[T] {
  def map[U](f: T => U): List[U] = {
    this match {
      case Nil => this
      case x :: xs => f(x) :: xs.map(f)
    }
  }

  def filter(f: T => Boolean): List[T] = this match {
    case Nil => this
    case x :: xs => if (f(x)) x :: xs.filter(f) else xs.filter(f)
  }
}

// then scalelist becomes...

def generalScaleList(xs: List[Double], factor: Double): List[Double] = {
  xs map(x => x _* factor)
}

def squareList(xs: List[Int]): List[Int] = {
  xs match {
    case Nil => Nil
    case y :: ys => y * y :: squareList(ys)
  }
}

def generalSquareList(xs: List[Int]): List[Int] = {
  xs.map(x => x * x)
}

// takewhile and dropwhile

val nums = List(-3, -2, -1, 0, 1, 2, 3)
nums takeWhile (x => x > 0)
nums dropWhile (x => x > 0)
nums span (x => x > 0) // returns (List[Int], List[Int])

// define a pack function that removes consecutive duplicates
def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

// encode produces run-length encoding of a list
encode(List("a", "a", "a", "b", "c", "c", "a"))
// should give
List(("a", 3), ("b", 1), ("c", 2), ("a", 1))
def encode[T](xs: List[T]): List[(T, Int)] = xs match {
  pack(xs) map (xs => (xs.head, xs.length))
}